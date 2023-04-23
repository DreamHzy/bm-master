package com.baimeng.bmmerchant.task;


import com.baimeng.bmcore.constants.CS;
import com.baimeng.bmmerchant.mapper.UserTaskMapper;
import com.baimeng.bmmerchant.model.vo.UserTaskStageVO;
import com.baimeng.bmservice.impl.IBTaskStageUserService;
import com.baimeng.bmservice.impl.IBTaskUserClockService;
import com.baimeng.bmservice.impl.IBTaskUserClockTaskService;
import com.baimeng.bmservice.model.BTaskStageUser;
import com.baimeng.bmservice.model.BTaskUserClock;
import com.baimeng.bmservice.model.BTaskUserClockTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class UserTask {
    @Resource
    UserTaskMapper userTaskMapper;
    @Resource
    IBTaskUserClockService ibTaskUserClockService;
    @Resource
    IBTaskUserClockTaskService ibTaskUserClockTaskService;


    @Scheduled(cron = "0 0 0 * * ?")
//    @Scheduled(cron = "0 */1 * * * ?")//每五分 测试用``
//    @PostConstruct
    public void taskJob() {
        //对过期任务或者生效任务进行操作
        //修改当天待失效的任务
        userTaskMapper.updateTaskStageUserInvalid();
        //修改当天待生效的任务
        userTaskMapper.updateTaskStageUserEffect();
        //添加用户打卡记录和任务详情记录
        log.info("查询用户任务阶段关联信息------------------------");
        //查询用户任务阶段关联信息
        List<UserTaskStageVO> userTaskStageVOS = userTaskMapper.queryList();
        if (userTaskStageVOS.size() > 0) {
            List<BTaskUserClock> bTaskUserClockList = new ArrayList<>();
            //添加用户待打卡记录
            userTaskStageVOS.stream().forEach(
                    userTaskStageVO -> {
                        BTaskUserClock bTaskUserClock = new BTaskUserClock();
                        bTaskUserClock.setStageId(userTaskStageVO.getStageId());
                        bTaskUserClock.setSysUserId(userTaskStageVO.getSysUserId());
                        bTaskUserClock.setStoreNo(userTaskStageVO.getStoreNo());
                        bTaskUserClock.setState(CS.YES);
                        bTaskUserClock.setEndAt(userTaskStageVO.getEndTime());
                        bTaskUserClock.setCreatedAt(new Date());
                        bTaskUserClockList.add(bTaskUserClock);
                    }
            );
            ibTaskUserClockService.saveBatch(bTaskUserClockList);
            //任务处理
            List<BTaskStageUser> bTaskStageUserList = userTaskMapper.bTaskStageUserList(bTaskUserClockList);
            if (bTaskStageUserList.size() > 0) {
                //查询用户任务信息
                List<BTaskUserClockTask> bTaskUserClockTaskList = new ArrayList<>();
                bTaskStageUserList.stream().forEach(
                        bTaskStageUser -> {
                            for (BTaskUserClock bTaskUserClock : bTaskUserClockList) {
                                Boolean result = bTaskStageUser.getStageId().equals(bTaskUserClock.getStageId())
                                        && bTaskStageUser.getSysUserId().equals(bTaskUserClock.getSysUserId());
                                if (result) {
                                    BTaskUserClockTask bTaskUserClockTask = new BTaskUserClockTask();
                                    bTaskUserClockTask.setClockId(bTaskUserClock.getClockId());
                                    bTaskUserClockTask.setSysUserId(bTaskUserClock.getSysUserId());
                                    bTaskUserClockTask.setTaskNo(bTaskStageUser.getTaskNo());
                                    bTaskUserClockTask.setState(CS.NO);
                                    bTaskUserClockTask.setEndAt(bTaskUserClock.getEndAt());
                                    bTaskUserClockTask.setCreatedAt(new Date());
                                    bTaskUserClockTaskList.add(bTaskUserClockTask);
                                    break;
                                }
                            }
                        }
                );
                if (bTaskUserClockTaskList.size() > 0) {
                    ibTaskUserClockTaskService.saveBatch(bTaskUserClockTaskList);
                }
            }
        }
        log.info("查询用户任务阶段关联信息结束------------------------");
    }

}
