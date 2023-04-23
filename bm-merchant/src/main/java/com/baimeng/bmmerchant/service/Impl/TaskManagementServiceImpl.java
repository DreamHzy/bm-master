package com.baimeng.bmmerchant.service.Impl;

import com.baimeng.bmcomponentsoss.config.AliyunOssYmlConfig;
import com.baimeng.bmcore.constants.CS;
import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmcore.utils.DateKit;
import com.baimeng.bmmerchant.enums.TaskClockEnum;
import com.baimeng.bmmerchant.mapper.StoreTaskMapper;
import com.baimeng.bmmerchant.mapper.TaskManagementMapper;
import com.baimeng.bmmerchant.model.dto.*;
import com.baimeng.bmmerchant.model.vo.StoreManagerTasklistVO;
import com.baimeng.bmmerchant.model.vo.TaskVO;
import com.baimeng.bmmerchant.model.vo.TasklistVO;
import com.baimeng.bmmerchant.service.TaskManagementService;
import com.baimeng.bmservice.impl.*;
import com.baimeng.bmservice.model.*;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskManagementServiceImpl implements TaskManagementService {

    @Resource
    TaskManagementMapper taskMapper;
    @Resource
    IBStoreSysUserService ibStoreSysUserService;
    @Resource
    IBTaskStageService ibTaskStageService;
    @Resource
    IBTaskStageUserService ibTaskStageUserService;
    @Resource
    StoreTaskMapper storeTaskMapper;
    @Resource
    IBTaskUserClockService ibTaskUserClockService;
    @Resource
    AliyunOssYmlConfig aliyunOssYmlConfig;
    @Resource
    IBFileService ibFileService;
    @Resource
    IBTaskUserClockTaskService ibTaskUserClockTaskService;
    @Resource
    IBTaskCourseService ibTaskCourseService;
    @Resource
    IBSysUserService ibSysUserService;
    @Resource
    IBTaskUserClockEvaluateService ibTaskUserClockEvaluateService;
    @Resource
    IBStoreService ibStoreService;

    @Override
    public ApiRes<TaskVO> taskManagementService(TaskManagementDTO taskDTO, JeeUserDetails jeeUserDetails) {
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);
        //在岗休息人数
        TodayDTO todayDTO = new TodayDTO();
        //在岗人数
        Integer onDutyAmount = taskMapper.onDutyAmount(taskDTO.getDate(), bStoreSysUser.getStoreNo());
        todayDTO.setOnDutyAmount(onDutyAmount + "");
        //门店总人数
        Integer storeSum = taskMapper.storeSum(bStoreSysUser.getStoreNo());
        //休息人数
        Integer restAmount = storeSum - onDutyAmount;
        todayDTO.setRestAmount(restAmount + "");
        BStore bStore = ibStoreService.getOne(BStore.gw().eq(BStore::getStoreNo, bStoreSysUser.getStoreNo()));
        //任务状态
        TaskStateDTO taskStateDTO = taskMapper.selectTaskState(taskDTO.getDate(), bStoreSysUser.getStoreNo());
        taskStateDTO.setStoreName(bStore.getStoreName());
        //用户任务状态
        List<TaskListDTO> taskDetailsDTO = taskMapper.selectDetails(taskDTO.getDate(), bStoreSysUser.getStoreNo(), aliyunOssYmlConfig.getUrl());
        TaskVO taskVO = new TaskVO();
        taskVO.setTodayDTO(todayDTO);
        taskVO.setTaskStateDTO(taskStateDTO);
        taskVO.setTaskList(taskDetailsDTO);
        return ApiRes.ok(taskVO);
    }

    @Override
    public ApiRes<StoreManagerTasklistVO> storeManagerTasklist(String day, String userId) {
        if (StringUtils.isAnyBlank(day, userId)) {
            return ApiRes.customFail("请输入检查内容");
        }
        BSysUser bSysUser = ibSysUserService.getById(userId);
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, userId)).get(0);
        //查询任务列表
        List<TasklistVO> tasklistVOList = storeTaskMapper.
                tasklistVOList(day, Integer.valueOf(userId), aliyunOssYmlConfig.getUrl(), bStoreSysUser.getStoreNo());
        tasklistVOList.stream().forEach(tasklistVO -> {
            tasklistVO.setStatusMsg(TaskClockEnum.getStatusDesc(tasklistVO.getStatus()));
        });
        StoreManagerTasklistVO taskInfoVO = new StoreManagerTasklistVO();
        taskInfoVO.setTasklistVOList(tasklistVOList);
        taskInfoVO.setSysName(bSysUser.getRealname());
        taskInfoVO.setSysNo(bSysUser.getUserNo());
        //查看评价信息
        BTaskUserClockEvaluate bTaskUserClockEvaluate = ibTaskUserClockEvaluateService.
                getOne(BTaskUserClockEvaluate.gw().
                        eq(BTaskUserClockEvaluate::getCreatedDay, day).
                        eq(BTaskUserClockEvaluate::getClockSysUserId, bSysUser.getSysUserId())
                        .eq(BTaskUserClockEvaluate::getState, CS.YES));
        List<String> files = new ArrayList<>();
        if (bTaskUserClockEvaluate != null) {
            //查询评价图片
            files = taskMapper.queryFiles(bTaskUserClockEvaluate.getEvaluateFileId());
            taskInfoVO.setScore(bTaskUserClockEvaluate.getEvaluateScore() + "");
            taskInfoVO.setMsg(bTaskUserClockEvaluate.getEvaluateMsg());
            taskInfoVO.setImagList(files);
        } else {
            taskInfoVO.setScore("");
            taskInfoVO.setMsg("");
            taskInfoVO.setImagList(files);
        }


        taskInfoVO.setOssUrl(aliyunOssYmlConfig.getUrl());
        return ApiRes.ok(taskInfoVO);
    }

    @Override
    public ApiRes evaluate(EvaluateDTO evaluateDTO, JeeUserDetails jeeUserDetails) {
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        String day = evaluateDTO.getDay();
        if (StringUtils.isAnyBlank(day)) {
            return ApiRes.customFail("请输入日期");
        }
        String msg = evaluateDTO.getMsg();
        if (StringUtils.isAnyBlank(msg)) {
            return ApiRes.customFail("请输入评价内容");
        }
        String score = evaluateDTO.getScore();
        if (StringUtils.isAnyBlank(score)) {
            return ApiRes.customFail("请输入分数");
        }
        List<String> urls = evaluateDTO.getImagList();

        String userId = evaluateDTO.getUserId();
        if (StringUtils.isAnyBlank(userId)) {
            return ApiRes.customFail("请输入userId");
        }
        if (!DateKit.isToday(day)) {
            return ApiRes.customFail("只能评价今天的内容");
        }
        //先查询当天这个有无评价
        BTaskUserClockEvaluate bTaskUserClockEvaluate = ibTaskUserClockEvaluateService.
                getOne(BTaskUserClockEvaluate.gw().
                        eq(BTaskUserClockEvaluate::getCreatedDay, day).
                        eq(BTaskUserClockEvaluate::getClockSysUserId, evaluateDTO.getUserId())
                        .eq(BTaskUserClockEvaluate::getState, CS.YES));
        if (bTaskUserClockEvaluate != null) {
            bTaskUserClockEvaluate.setState(CS.NO);
            bTaskUserClockEvaluate.setUpdatedAt(new Date());
            ibTaskUserClockEvaluateService.updateById(bTaskUserClockEvaluate);
        }


        BTaskUserClockEvaluate bTaskUserClockEvaluateNew = new BTaskUserClockEvaluate();
        bTaskUserClockEvaluateNew.setEvaluateMsg(msg);
        bTaskUserClockEvaluateNew.setSysUserId(bSysUser.getSysUserId());
        bTaskUserClockEvaluateNew.setClockSysUserId(Integer.valueOf(userId));
        bTaskUserClockEvaluateNew.setEvaluateScore(Integer.valueOf(score));
        bTaskUserClockEvaluateNew.setCreatedAt(new Date());
        bTaskUserClockEvaluateNew.setCreatedDay(new Date());
        ibTaskUserClockEvaluateService.save(bTaskUserClockEvaluateNew);
        if (urls.size() > 0) {
            List<BFile> fileList = new ArrayList<>();
            for (String s : urls) {
                BFile bFile = new BFile();
                bFile.setUrl(s);
                bFile.setCreatedAt(new Date());
                bFile.setState(CS.YES);
                bFile.setType(CS.FOUR_INT);
                bFile.setBelongInfo("b_task_user_clock_evaluate");
                bFile.setBelongInfoId(bTaskUserClockEvaluateNew.getEvaluateFileId() + "");
                bFile.setCreatedAt(new Date());
                fileList.add(bFile);
            }
            if (fileList.size() > 0) {
                ibFileService.saveBatch(fileList);
            }
        }
        return ApiRes.ok();
    }


}