package com.baimeng.bmmerchant.ctrl;

import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.model.vo.QueryClockInfoVO;
import com.baimeng.bmmerchant.model.vo.TaskDetailVO;
import com.baimeng.bmmerchant.service.ManageTaskService;
import com.baimeng.bmmerchant.service.StoreTaskService;
import com.baimeng.bmmerchant.service.TaskManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "公用接口")
@RestController
@RequestMapping("/commonn")
@Slf4j
public class CommonController extends CommonCtrl {

    @Resource
    StoreTaskService storeTaskService;


    @ApiOperation("任务详情")
    @RequestMapping(value = "/taskDetail", method = RequestMethod.GET)
    public ApiRes<TaskDetailVO> managerTaskDetail(@ApiParam(name = "clockId", value = "阶段id", required = true) @NonNull @RequestParam("clockId") String clockId) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return storeTaskService.taskDetail(clockId, jeeUserDetails);
    }

    /**
     * 查看打卡
     */
    @ApiOperation("查看打卡")
    @RequestMapping(value = "/queryClockInfo", method = RequestMethod.GET)
    public ApiRes<QueryClockInfoVO> managerQueryClockInfo(@ApiParam(name = "clockId", value = "阶段id", required = true) @NonNull @RequestParam("clockId") String clockId) {
        return storeTaskService.queryClockInfo(clockId);
    }

    /**
     * 查看教程
     */
    @ApiOperation("查看教程")
    @RequestMapping(value = "/queryCourse", method = RequestMethod.GET)
    public ApiRes managerQueryCourse(@ApiParam(name = "taskUserClockTaskId", value = "任务id", required = true) @NonNull @RequestParam("taskUserClockTaskId") String taskUserClockTaskId) {
        return storeTaskService.queryCourse(taskUserClockTaskId);
    }


}
