package com.baimeng.bmmerchant.service.Impl;

import com.baimeng.bmcomponentsoss.config.AliyunOssYmlConfig;
import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.mapper.ManageTaskMapper;
import com.baimeng.bmmerchant.model.dto.TaskDTO;
import com.baimeng.bmmerchant.model.dto.TaskListDTO;
import com.baimeng.bmmerchant.model.dto.TaskStateDTO;
import com.baimeng.bmmerchant.model.dto.TodayDTO;
import com.baimeng.bmmerchant.model.vo.StoreListVO;
import com.baimeng.bmmerchant.model.vo.TaskVO;
import com.baimeng.bmmerchant.service.ManageTaskService;
import com.baimeng.bmservice.impl.IBStoreService;
import com.baimeng.bmservice.impl.IBStoreSysUserService;
import com.baimeng.bmservice.model.BStoreSysUser;
import com.baimeng.bmservice.model.BSysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ManageTaskServiceImpl implements ManageTaskService {
    @Resource
    ManageTaskMapper manageTaskMapper;
    @Resource
    IBStoreSysUserService ibStoreSysUserService;
    @Resource
    AliyunOssYmlConfig aliyunOssYmlConfig;
    @Resource
    IBStoreService ibStoreService;


    @Override
    public ApiRes<StoreListVO> storeList(JeeUserDetails jeeUserDetails) {
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        List<StoreListVO> storeListVOS = manageTaskMapper.storeList(bSysUser.getSysUserId());
        return ApiRes.ok(storeListVOS);
    }

    @Override
    public ApiRes<TaskVO> taskList(TaskDTO taskDTO, JeeUserDetails jeeUserDetails) {
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);
        TaskVO taskVO = new TaskVO();
        List<String> onDutyAmountTest = new ArrayList<>();
        if (Objects.equals(taskDTO.getStoreNo(), "")) {
            //用户门店下所有的id
            onDutyAmountTest = manageTaskMapper.onDutyAmount(bStoreSysUser.getSysUserId());
        } else {
            onDutyAmountTest.add(taskDTO.getStoreNo());
        }
        //在岗休息人数
        TodayDTO todayDTO = new TodayDTO();
        //在岗人数
        Integer onDutyAmounts = manageTaskMapper.onDutyAmounts(taskDTO.getDate(), onDutyAmountTest);
        todayDTO.setOnDutyAmount(onDutyAmounts + "");
        //门店总人数
        Integer storeSum = manageTaskMapper.storeSum(onDutyAmountTest);
        //休息人数
        Integer restAmount = storeSum - onDutyAmounts;
        todayDTO.setRestAmount(restAmount + "");
        //任务状态
        TaskStateDTO taskStateDTO = manageTaskMapper.selectTaskState(taskDTO.getDate(), onDutyAmountTest);
        //用户任务状态
        List<TaskListDTO> taskDetailsDTO = manageTaskMapper.selectDetailsList(taskDTO.getDate(), onDutyAmountTest, aliyunOssYmlConfig.getUrl());
        taskVO.setTodayDTO(todayDTO);
        taskVO.setTaskStateDTO(taskStateDTO);
        taskVO.setTaskList(taskDetailsDTO);
        return ApiRes.ok(taskVO);

//        //在岗休息人数
//        TodayDTO todayDTO = new TodayDTO();
//        //在岗人数
//        Integer onDutyAmounts = manageTaskMapper.selectDutyAmount(taskDTO.getDate(), onDutyAmountTest);
//        todayDTO.setOnDutyAmount(onDutyAmounts + "");
//        //门店总人数
//        Integer storeSum = manageTaskMapper.storeAllSum(bStoreSysUser.getStoreNo());
//        //休息人数
//        Integer restAmount = storeSum - onDutyAmounts;
//        todayDTO.setRestAmount(restAmount + "");
//        taskVO.setTodayDTO(todayDTO);
//        //任务状态
//        TaskStateDTO taskStateDTO = manageTaskMapper.selectTaskstatus(taskDTO.getDate(), bStoreSysUser.getStoreNo());
//        taskVO.setTaskStateDTO(taskStateDTO);
//        //用户任务状态
//        List<TaskListDTO> taskDetailsDTO = manageTaskMapper.selectDetailsLists(taskDTO.getDate(), bStoreSysUser.getStoreNo(), aliyunOssYmlConfig.getUrl());
//        taskVO.setTaskList(taskDetailsDTO);
//        return ApiRes.ok(taskVO);

//        //如果不传门店编号就查全部
//        if (Objects.equals(taskDTO.getStoreNo(), "")) {
//            //在岗休息人数
//            TodayDTO todayDTO = new TodayDTO();
//            //用户门店下所有的id
//            List<String> onDutyAmount = manageTaskMapper.onDutyAmount(bStoreSysUser.getSysUserId());
//            //在岗人数
//            Integer onDutyAmounts = manageTaskMapper.onDutyAmounts(taskDTO.getDate(), onDutyAmount);
//            todayDTO.setOnDutyAmount(onDutyAmounts + "");
//            //门店总人数
//            Integer storeSum = manageTaskMapper.storeSum(onDutyAmount);
//            //休息人数
//            Integer restAmount = storeSum - onDutyAmounts;
//            todayDTO.setRestAmount(restAmount + "");
//            //任务状态
//            TaskStateDTO taskStateDTO = manageTaskMapper.selectTaskState(taskDTO.getDate(), onDutyAmount);
//            //用户任务状态
//            List<TaskListDTO> taskDetailsDTO = manageTaskMapper.selectDetailsList(taskDTO.getDate(), onDutyAmount, aliyunOssYmlConfig.getUrl());
//            taskVO.setTodayDTO(todayDTO);
//            taskVO.setTaskStateDTO(taskStateDTO);
//            taskVO.setTaskList(taskDetailsDTO);
//            return ApiRes.ok(taskVO);
//        } else {
//            //在岗休息人数
//            TodayDTO todayDTO = new TodayDTO();
//            //在岗人数
//            Integer onDutyAmount = manageTaskMapper.selectDutyAmount(taskDTO.getDate(), bStoreSysUser.getStoreNo());
//            todayDTO.setOnDutyAmount(onDutyAmount + "");
//            //门店总人数
//            Integer storeSum = manageTaskMapper.storeAllSum(bStoreSysUser.getStoreNo());
//            //休息人数
//            Integer restAmount = storeSum - onDutyAmount;
//            todayDTO.setRestAmount(restAmount + "");
//            taskVO.setTodayDTO(todayDTO);
//            //任务状态
//            TaskStateDTO taskStateDTO = manageTaskMapper.selectTaskstatus(taskDTO.getDate(), bStoreSysUser.getStoreNo());
//            taskVO.setTaskStateDTO(taskStateDTO);
//            //用户任务状态
//            List<TaskListDTO> taskDetailsDTO = manageTaskMapper.selectDetailsLists(taskDTO.getDate(), bStoreSysUser.getStoreNo(), aliyunOssYmlConfig.getUrl());
//            taskVO.setTaskList(taskDetailsDTO);
//            return ApiRes.ok(taskVO);
//        }
    }
}