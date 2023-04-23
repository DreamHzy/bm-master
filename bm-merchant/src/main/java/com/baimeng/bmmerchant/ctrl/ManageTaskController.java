package com.baimeng.bmmerchant.ctrl;


import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.model.dto.TaskDTO;
import com.baimeng.bmmerchant.model.vo.*;
import com.baimeng.bmmerchant.service.ManageTaskService;
import com.baimeng.bmmerchant.service.StoreTaskService;
import com.baimeng.bmmerchant.service.TaskManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "管理员-任务相关接口")
@RestController
@RequestMapping("/manageTask")
@Slf4j
public class ManageTaskController extends CommonCtrl {

    @Resource
    ManageTaskService manageTaskService;
    @Resource
    TaskManagementService taskManagementService;
    @Resource
    StoreTaskService storeTaskService;

    /**
     * 门店列表下拉框
     */
    @ApiOperation("门店列表下拉框")
    @PostMapping("/storeList")
    public ApiRes<StoreListVO> storeList() {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return manageTaskService.storeList(jeeUserDetails);
    }

    @ApiOperation("任务管理")
    @PostMapping("/managementTask")
    public ApiRes<TaskVO> managementTask(@RequestBody TaskDTO taskDTO) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return manageTaskService.taskList(taskDTO,jeeUserDetails);
    }

    /**
     * 任务列表
     */
    @ApiOperation("任务列表")
    @RequestMapping(value = "/managerTasklist", method = RequestMethod.GET)
    public ApiRes<StoreManagerTasklistVO> managerTasklist(@ApiParam(name = "day", value = "时间", required = true) @NonNull @RequestParam("day") String day, @ApiParam(name = "userId", value = "用户id", required = true) @NonNull @RequestParam("userId") String userId) {
        return taskManagementService.storeManagerTasklist(day, userId);
    }
}
