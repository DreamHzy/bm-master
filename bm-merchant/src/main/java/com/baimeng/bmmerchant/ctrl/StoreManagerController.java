package com.baimeng.bmmerchant.ctrl;

import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.model.dto.AddTaskDTO;
import com.baimeng.bmmerchant.model.dto.EvaluateDTO;
import com.baimeng.bmmerchant.model.dto.QueryTaskBySceneIdDTO;
import com.baimeng.bmmerchant.model.dto.TaskManagementDTO;
import com.baimeng.bmmerchant.model.vo.*;
import com.baimeng.bmmerchant.service.StoreTaskService;
import com.baimeng.bmmerchant.service.TaskManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "店长-任务管理")
@RestController
@RequestMapping("/storeManager")
@Slf4j
public class StoreManagerController extends CommonCtrl {

    @Resource
    TaskManagementService taskManagementService;
    @Resource
    StoreTaskService storeTaskService;


    @ApiOperation("任务管理")
    @PostMapping("/taskManagement")
    public ApiRes<TaskVO> taskManagement(@RequestBody TaskManagementDTO taskDTO) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return taskManagementService.taskManagementService(taskDTO, jeeUserDetails);
    }

    /**
     * 任务列表
     */
    @ApiOperation("任务列表")
    @RequestMapping(value = "/storeManagerTasklist", method = RequestMethod.GET)
    public ApiRes<StoreManagerTasklistVO> tasklist(@ApiParam(name = "day", value = "时间", required = true) @NonNull @RequestParam("day") String day, @ApiParam(name = "userId", value = "用户id", required = true) @NonNull @RequestParam("userId") String userId) {
        return taskManagementService.storeManagerTasklist(day, userId);
    }

    /**
     * 评价
     */
    @ApiOperation("评价（修改或者新增）")
    @RequestMapping(value = "/evaluate", method = RequestMethod.POST)
    public ApiRes evaluate(@RequestBody EvaluateDTO evaluateDTO) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return taskManagementService.evaluate(evaluateDTO, jeeUserDetails);
    }


    /**
     * 任务分配列表
     */
    @ApiOperation("任务分配列表")
    @RequestMapping(value = "/distributionTaskList", method = RequestMethod.GET)
    public ApiRes<DistributionTaskListVO> distributionTaskList(@ApiParam(name = "userId", value = "用户id", required = true) @NonNull @RequestParam("userId") String userId) {
        return storeTaskService.distributionTaskList(userId);
    }

    /**
     * 移除任务
     */
    @ApiOperation("移除任务")
    @RequestMapping(value = "/remveTask", method = RequestMethod.GET)
    public ApiRes remveTask(@ApiParam(name = "taskStageUserId", value = "任务id", required = true) @NonNull @RequestParam("taskStageUserId") String taskStageUserId) {
        return storeTaskService.remveTask(taskStageUserId);
    }


    /**
     * 恢复任务
     */
    @ApiOperation("恢复任务")
    @RequestMapping(value = "/recoveryTask", method = RequestMethod.GET)
    public ApiRes recoveryTask(@ApiParam(name = "taskStageUserId", value = "任务id", required = true) @NonNull @RequestParam("taskStageUserId") String taskStageUserId) {
        return storeTaskService.recoveryTask(taskStageUserId);
    }


    /**
     * 场景类别列表
     */
    @ApiOperation("场景类别列表")
    @RequestMapping(value = "/sceneList", method = RequestMethod.GET)
    public ApiRes sceneList() {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return storeTaskService.sceneList(jeeUserDetails);
    }
//

    /**
     * 根据场景查询任务
     */
    @ApiOperation("根据场景查询任务")
    @RequestMapping(value = "/queryTaskBySceneId", method = RequestMethod.POST)
    public ApiRes<QueryTaskBySceneIdVO> queryTaskBySceneId(@RequestBody QueryTaskBySceneIdDTO queryTaskBySceneIdDTO) {
        return storeTaskService.queryTaskBySceneId(queryTaskBySceneIdDTO);
    }

    /**
     * 添加任务
     */
    @ApiOperation("添加任务")
    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public ApiRes addTask(@RequestBody AddTaskDTO addTaskDTO) {
        return storeTaskService.addTask(addTaskDTO);
    }
}
