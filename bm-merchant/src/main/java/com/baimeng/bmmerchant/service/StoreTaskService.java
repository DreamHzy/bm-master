package com.baimeng.bmmerchant.service;

import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.model.dto.AddTaskDTO;
import com.baimeng.bmmerchant.model.dto.PunchTheClockDTO;
import com.baimeng.bmmerchant.model.dto.QueryTaskBySceneIdDTO;
import com.baimeng.bmmerchant.model.dto.UpdatetaskDTO;
import com.baimeng.bmmerchant.model.vo.*;

public interface StoreTaskService {
    ApiRes<TaskInfoVO> tasklist(String day, JeeUserDetails jeeUserDetails );

    ApiRes punchTheClock(PunchTheClockDTO punchTheClockDTO);

    ApiRes<TaskDetailVO> taskDetail(String clockId, JeeUserDetails jeeUserDetails);

    ApiRes<QueryClockInfoVO> queryClockInfo(String clockId);

    ApiRes updatetask(UpdatetaskDTO updatetaskDTO);

    ApiRes queryCourse(String taskUserClockTaskId);

    ApiRes<DistributionTaskListVO> distributionTaskList(  String sysUserId );

    ApiRes remveTask(String taskStageUserId);

    ApiRes recoveryTask(String taskStageUserId);

    ApiRes sceneList(   JeeUserDetails jeeUserDetails);

    ApiRes<QueryTaskBySceneIdVO> queryTaskBySceneId(QueryTaskBySceneIdDTO queryTaskBySceneIdDTO);

    ApiRes addTask(AddTaskDTO addTaskDTO);
}
