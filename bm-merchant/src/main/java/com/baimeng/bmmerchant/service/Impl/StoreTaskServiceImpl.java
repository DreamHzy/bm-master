package com.baimeng.bmmerchant.service.Impl;

import com.baimeng.bmcomponentsoss.config.AliyunOssYmlConfig;
import com.baimeng.bmcore.constants.CS;
import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmcore.utils.DateKit;
import com.baimeng.bmmerchant.enums.DistributisonStatusEnum;
import com.baimeng.bmmerchant.enums.TaskClockEnum;
import com.baimeng.bmmerchant.enums.TaskDetailEnum;
import com.baimeng.bmmerchant.mapper.StoreTaskMapper;
import com.baimeng.bmmerchant.model.dto.AddTaskDTO;
import com.baimeng.bmmerchant.model.dto.PunchTheClockDTO;
import com.baimeng.bmmerchant.model.dto.QueryTaskBySceneIdDTO;
import com.baimeng.bmmerchant.model.dto.UpdatetaskDTO;
import com.baimeng.bmmerchant.model.vo.*;
import com.baimeng.bmmerchant.service.StoreTaskService;
import com.baimeng.bmservice.impl.*;
import com.baimeng.bmservice.model.*;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StoreTaskServiceImpl implements StoreTaskService {
    @Resource
    IBTaskStageService ibTaskStageService;

    @Resource
    StoreTaskMapper storeTaskMapper;
    @Resource
    IBTaskUserClockService ibTaskUserClockService;
    @Resource
    AliyunOssYmlConfig aliyunOssYmlConfig;
    @Resource
    IBStoreSysUserService ibStoreSysUserService;
    @Resource
    IBFileService ibFileService;
    @Resource
    IBTaskUserClockTaskService ibTaskUserClockTaskService;
    @Resource
    IBTaskCourseService ibTaskCourseService;
    @Resource
    IBTaskStageUserService ibTaskStageUserService;
    @Resource
    IBSysUserService ibSysUserService;
    @Resource
    IBTaskSceneService ibTaskSceneService;
    @Resource
    IBTaskService ibTaskService;

    @Override
    public ApiRes<TaskInfoVO> tasklist(String day, JeeUserDetails jeeUserDetails) {
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);
        List<TasklistVO> tasklistVOList = storeTaskMapper.tasklistVOList(day, bSysUser.getSysUserId(), aliyunOssYmlConfig.getUrl(), bStoreSysUser.getStoreNo());
        tasklistVOList.stream().forEach(tasklistVO -> {
            tasklistVO.setStatusMsg(TaskClockEnum.getStatusDesc(tasklistVO.getStatus()));
        });
        TaskInfoVO taskInfoVO = new TaskInfoVO();
        taskInfoVO.setTasklistVOList(tasklistVOList);
        taskInfoVO.setSysName(bSysUser.getRealname());
        taskInfoVO.setSysNo(bSysUser.getUserNo());
        return ApiRes.ok(taskInfoVO);
    }

    @Override
    public ApiRes punchTheClock(PunchTheClockDTO punchTheClockDTO) {
        String clockId = punchTheClockDTO.getClockId();
        String imageUrl = punchTheClockDTO.getImageUrl();
        String address = punchTheClockDTO.getAddress();
        if (StringUtils.isAnyBlank(clockId, imageUrl)) {
            return ApiRes.customFail("请输入检查内容");
        }
        //查询需要打开的记录是否存在以及状态是否正确
        BTaskUserClock bTaskUserClock = ibTaskUserClockService.getById(punchTheClockDTO.getClockId());
        if (bTaskUserClock == null) {
            return ApiRes.customFail("打开记录不存在");
        }
        if (bTaskUserClock.getState() != CS.NO) {
            return ApiRes.customFail("该记录打打卡状态不对");
        }
        bTaskUserClock.setAddress(address);
        bTaskUserClock.setState(CS.YES);
        bTaskUserClock.setClockDayAt(new Date());
        bTaskUserClock.setClockAt(new Date());
        bTaskUserClock.setUpdatedAt(new Date());
        BFile bFile = new BFile();
        bFile.setBelongInfoId(bTaskUserClock.getClockId() + "");
        bFile.setBelongInfo("b_task_user_clock");
        bFile.setType(CS.FOUR_INT);
        bFile.setName(punchTheClockDTO.getFileName());
        bFile.setUrl(punchTheClockDTO.getImageUrl());
        bFile.setState(CS.YES);
        bFile.setCreatedAt(new Date());
        if (!ibTaskUserClockService.updateById(bTaskUserClock)) {
            return ApiRes.customFail("打卡记录更新失败");
        }
        if (!ibFileService.save(bFile)) {
            return ApiRes.customFail("图片存储失败");
        }
        return ApiRes.ok();
    }

    @Override
    public ApiRes<TaskDetailVO> taskDetail(String clockId, JeeUserDetails jeeUserDetails) {
        if (StringUtils.isAnyBlank(clockId)) {
            return ApiRes.customFail("请输入检查内容");
        }
        //查询需要打开的记录是否存在以及状态是否正确
        BTaskUserClock bTaskUserClock = ibTaskUserClockService.getById(clockId);
        if (bTaskUserClock == null) {
            return ApiRes.customFail("打开记录不存在");
        }
        //查询阶段信息
        BTaskStage bTaskStage = ibTaskStageService.getById(bTaskUserClock.getStageId());

        if (bTaskStage == null) {
            return ApiRes.customFail("阶段信息不存在");
        }
        //用户信息
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        TaskDetailVO taskDetailVO = new TaskDetailVO();
        taskDetailVO.setSysName(bSysUser.getRealname());
        taskDetailVO.setSysNo(bSysUser.getUserNo());
        taskDetailVO.setClockId(clockId);
        taskDetailVO.setStageName(bTaskStage.getName());
        List<TaskDetailListVO> taskDetailListVOS = storeTaskMapper.taskDetailListVOS(clockId);
        taskDetailListVOS.stream().forEach(taskDetailListVO -> {
            taskDetailListVO.setStatusMsg(TaskDetailEnum.getStatusDesc(taskDetailListVO.getStatus()));
            taskDetailListVO.setImageUrl(aliyunOssYmlConfig.getUrl() + taskDetailListVO.getImageUrl());
            String startTime = new SimpleDateFormat("HH:mm").format(taskDetailListVO.getEndAt());
            String endDate = new SimpleDateFormat("HH:mm").format(new Date());
            int resultTime = endDate.compareTo(startTime);
            if (DateKit.isToday(taskDetailListVO.getCreatedAt())) {//判断是否是今天
                //点击按钮是否弹出逾期
                if (resultTime < 0) {
                    taskDetailListVO.setOverdue(CS.NO + "");
                } else {
                    taskDetailListVO.setOverdue(CS.YES + "");
                }

            } else {//非今天的都是逾期的
                taskDetailListVO.setOverdue(CS.YES + "");

            }

        });
        taskDetailVO.setTaskDetailListVOS(taskDetailListVOS);
        return ApiRes.ok(taskDetailVO);
    }

    @Override
    public ApiRes<QueryClockInfoVO> queryClockInfo(String clockId) {
        if (StringUtils.isAnyBlank(clockId)) {
            return ApiRes.customFail("请输入检查内容");
        }
        //查询需要打开的记录是否存在以及状态是否正确
        BTaskUserClock bTaskUserClock = ibTaskUserClockService.getById(clockId);
        if (bTaskUserClock == null) {
            return ApiRes.customFail("打开记录不存在");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        BFile bFile = ibFileService.list(BFile.gw().eq(BFile::getBelongInfoId, clockId).eq(BFile::getBelongInfo, "b_task_user_clock")).get(0);
        QueryClockInfoVO queryClockInfoVO = new QueryClockInfoVO();
        queryClockInfoVO.setTime(sdf.format(bTaskUserClock.getClockAt()));
        queryClockInfoVO.setAddress(bTaskUserClock.getAddress());
        queryClockInfoVO.setIamgeUrl(aliyunOssYmlConfig.getUrl() + bFile.getUrl());
        return ApiRes.ok(queryClockInfoVO);
    }

    @Override
    public ApiRes updatetask(UpdatetaskDTO updatetaskDTO) {
        String taskUserClockTaskId = updatetaskDTO.getTaskUserClockTaskId();
        String msg = updatetaskDTO.getMsg();
        if (StringUtils.isAnyBlank(taskUserClockTaskId)) {
            return ApiRes.customFail("请输入taskUserClockTaskId");
        }
        BTaskUserClockTask bTaskUserClockTask = ibTaskUserClockTaskService.getById(taskUserClockTaskId);
        if (bTaskUserClockTask == null) {
            return ApiRes.customFail("任务不存在");
        }
        bTaskUserClockTask.setRemark(msg);
        bTaskUserClockTask.setFinshAt(new Date());
        //先判断是否是当天的任务
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        if (DateKit.isToday(sf.format(bTaskUserClockTask.getCreatedAt()))) {
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            try {
                Date endTime = df.parse(df.format(bTaskUserClockTask.getEndAt()));
                Date startTime = df.parse(df.format(new Date()));
                if (endTime.compareTo(startTime) > 0) {
                    bTaskUserClockTask.setState(CS.YES);
                } else {
                    bTaskUserClockTask.setState(CS.TWO);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {//如果不是当天的任务都应该逾期处理
            bTaskUserClockTask.setState(CS.TWO);
        }
        bTaskUserClockTask.setUpdatedAt(new Date());
        ibTaskUserClockTaskService.updateById(bTaskUserClockTask);
        //查看是否还有未完成的任务
        Integer count = ibTaskUserClockTaskService.count(BTaskUserClockTask.gw().
                eq(BTaskUserClockTask::getClockId, bTaskUserClockTask.getClockId()).eq(BTaskUserClockTask::getState, CS.NO));
        Integer oveduecount = 0;
        if (count == 0) {
            //查看是否有逾期的任务
            oveduecount = ibTaskUserClockTaskService.count(BTaskUserClockTask.gw().
                    eq(BTaskUserClockTask::getClockId, bTaskUserClockTask.getClockId()).eq(BTaskUserClockTask::getState, CS.TWO));
        }
        if (count == 0) {//修改任务
            //查询需要打开的记录是否存在以及状态是否正确
            BTaskUserClock bTaskUserClock = ibTaskUserClockService.getById(bTaskUserClockTask.getClockId());
            if (oveduecount == 0) {
                bTaskUserClock.setState(CS.TWO);
            } else {
                bTaskUserClock.setState(CS.THREE);
            }
            bTaskUserClock.setUpdatedAt(new Date());
            ibTaskUserClockService.updateById(bTaskUserClock);
        }
        return ApiRes.ok();
    }

    @Override
    public ApiRes queryCourse(String taskUserClockTaskId) {
        BTaskUserClockTask bTaskUserClockTask = ibTaskUserClockTaskService.getById(taskUserClockTaskId);
        if (bTaskUserClockTask == null) {
            return ApiRes.customFail("任务不存在");
        }
        //查看教程
        BTaskCourse bTaskCourse = ibTaskCourseService.getOne(BTaskCourse.gw().eq(BTaskCourse::getTaskNo, bTaskUserClockTask.getTaskNo()));
        if (bTaskCourse == null) {
            return ApiRes.customFail("暂无教程");
        }
        return ApiRes.ok(bTaskCourse.getContent());
    }

    @Override
    public ApiRes<DistributionTaskListVO> distributionTaskList(String sysUserId) {
        DistributionTaskListVO distributionTaskListVO = new DistributionTaskListVO();
        List<DistributionStageVO> distributionStageVOS = new ArrayList<>();
        BSysUser bSysUser = ibSysUserService.getById(sysUserId);
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);
        distributionTaskListVO.setUserName(bSysUser.getRealname());
        distributionTaskListVO.setUserNo(bSysUser.getUserNo());
        //查询门店任务阶段
        List<BTaskStage> bTaskStageList = ibTaskStageService.
                list(BTaskStage.gw().eq(BTaskStage::getStoreNo, bStoreSysUser.getStoreNo())
                        .eq(BTaskStage::getState, CS.YES)
                );
        if (bTaskStageList.size() > 0) {
            //查询阶段的任务
            List<DistributionTaskListDetailVO> distributionTaskListDetailVOS = storeTaskMapper.distributionTaskListDetailVOS(bStoreSysUser.getStoreNo(), bSysUser.getSysUserId());
            bTaskStageList.stream().forEach(
                    bTaskStage -> {
                        DistributionStageVO distributionStageVO = new DistributionStageVO();
                        distributionStageVO.setStageId(bTaskStage.getStageId() + "");
                        distributionStageVO.setName(bTaskStage.getName());
                        distributionStageVO.setUrl(aliyunOssYmlConfig.getUrl() + bTaskStage.getIconUrl());
                        distributionStageVO.setStartTime(new SimpleDateFormat("HH:mm").format(bTaskStage.getStartTime()));
                        distributionStageVO.setEndTime(new SimpleDateFormat("HH:mm").format(bTaskStage.getEndTime()));
                        List<DistributionTaskListDetailVO> distributionTaskListDetail = new ArrayList<>();
                        distributionTaskListDetailVOS.stream().forEach(
                                distributionTaskListDetailVO -> {
                                    distributionTaskListDetailVO.
                                            setStateMsg(DistributisonStatusEnum.getStatusDesc(distributionTaskListDetailVO.getState()));
                                    if (bTaskStage.getStageId().equals(distributionTaskListDetailVO.getStageId())) {
                                        distributionTaskListDetail.add(distributionTaskListDetailVO);
                                    }
                                }
                        );
                        distributionStageVO.setDistributionTaskListDetailVOS(distributionTaskListDetail);
                        distributionStageVOS.add(distributionStageVO);
                    }
            );
        }
        distributionTaskListVO.setDistributionStageVOS(distributionStageVOS);
        return ApiRes.ok(distributionTaskListVO);
    }

    @Override
    public ApiRes remveTask(String taskStageUserId) {
        BTaskStageUser bTaskStageUser = ibTaskStageUserService.getById(taskStageUserId);
        if (bTaskStageUser == null) {
            return ApiRes.customFail("任务不存在");
        }
        if (bTaskStageUser.getState() == 2) {
            bTaskStageUser.setState(CS.NO);
        } else {
            bTaskStageUser.setState(CS.THREE);
            bTaskStageUser.setInvalidAt(DateKit.getTomorrow());
        }
        bTaskStageUser.setUpdatedAt(new Date());
        ibTaskStageUserService.updateById(bTaskStageUser);
        return ApiRes.ok();
    }

    @Override
    public ApiRes recoveryTask(String taskStageUserId) {
        BTaskStageUser bTaskStageUser = ibTaskStageUserService.getById(taskStageUserId);
        if (bTaskStageUser == null) {
            return ApiRes.customFail("任务不存在");
        }
        if (bTaskStageUser.getState() == 3) {
            bTaskStageUser.setState(CS.YES);
        } else {
            bTaskStageUser.setState(CS.TWO);
            bTaskStageUser.setEffectAt(DateKit.getTomorrow());
        }
        bTaskStageUser.setUpdatedAt(new Date());
        ibTaskStageUserService.updateById(bTaskStageUser);
        return ApiRes.ok();
    }

    @Override
    public ApiRes sceneList(JeeUserDetails jeeUserDetails) {
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);
        List<BTaskScene> bTaskSceneList = ibTaskSceneService.list(BTaskScene.gw().eq(BTaskScene::getStoreNo, bStoreSysUser.getStoreNo())
                .eq(BTaskScene::getState, CS.YES));
        return ApiRes.ok(bTaskSceneList);
    }

    @Override
    public ApiRes<QueryTaskBySceneIdVO> queryTaskBySceneId(QueryTaskBySceneIdDTO queryTaskBySceneIdDTO) {
        String userId = queryTaskBySceneIdDTO.getUserId();
        if (StringUtils.isAnyBlank(userId)) {
            return ApiRes.customFail("请输入userId");
        }
        String sceneId = queryTaskBySceneIdDTO.getSceneId();
        if (StringUtils.isAnyBlank(sceneId)) {
            return ApiRes.customFail("请输入sceneId");
        }
        String stageId = queryTaskBySceneIdDTO.getStageId();
        if (StringUtils.isAnyBlank(stageId)) {
            return ApiRes.customFail("请输入stageId");
        }
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId,
                userId)).get(0);
        List<QueryTaskBySceneIdVO> bTaskList = storeTaskMapper.queryTaskBySceneId(userId, sceneId, stageId, bStoreSysUser.getStoreNo());
        return ApiRes.ok(bTaskList);
    }

    @Override
    public ApiRes addTask(AddTaskDTO addTaskDTO) {
        List<String> taskNos = addTaskDTO.getTaskNo();
        if (taskNos.size() <= 0) {
            return ApiRes.customFail("请输入taskNos");
        }
        String userId = addTaskDTO.getUserId();
        if (StringUtils.isAnyBlank(userId)) {
            return ApiRes.customFail("请输入userId");
        }
        String stageId = addTaskDTO.getStageId();
        if (StringUtils.isAnyBlank(stageId)) {
            return ApiRes.customFail("请输入stageId");
        }
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId,
                userId)).get(0);
        List<BTaskStageUser> bTaskStageUserList = new ArrayList<>();
        taskNos.stream().forEach(
                s -> {
                    BTaskStageUser bTaskStageUser = new BTaskStageUser();
                    bTaskStageUser.setTaskNo(s);
                    bTaskStageUser.setStageId(Integer.valueOf(stageId));
                    bTaskStageUser.setSysUserId(bStoreSysUser.getSysUserId());
                    bTaskStageUser.setStoreNo(bStoreSysUser.getStoreNo());
                    bTaskStageUser.setState(CS.TWO);
                    bTaskStageUser.setEffectAt(DateKit.getTomorrow());
                    bTaskStageUser.setCreatedAt(new Date());
                    bTaskStageUserList.add(bTaskStageUser);

                }
        );
        ibTaskStageUserService.saveBatch(bTaskStageUserList);
        return ApiRes.ok();
    }
}
