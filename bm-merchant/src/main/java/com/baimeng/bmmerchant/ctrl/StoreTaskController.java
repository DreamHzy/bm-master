package com.baimeng.bmmerchant.ctrl;

import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.model.dto.PunchTheClockDTO;
import com.baimeng.bmmerchant.model.dto.UpdatetaskDTO;
import com.baimeng.bmmerchant.model.vo.QueryClockInfoVO;
import com.baimeng.bmmerchant.model.vo.TaskDetailVO;
import com.baimeng.bmmerchant.model.vo.TaskInfoVO;
import com.baimeng.bmmerchant.service.StoreTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "任务打卡相关接口")
@RestController
@RequestMapping("/taskClock")
@Slf4j
public class StoreTaskController extends CommonCtrl {

    @Resource
    StoreTaskService storeTaskService;

    /**
     * 任务列表
     */
    @ApiOperation("任务列表")
    @RequestMapping(value = "/tasklist", method = RequestMethod.GET)
    public ApiRes<TaskInfoVO> tasklist(@ApiParam(name = "day", value = "时间", required = true) @NonNull @RequestParam("day") String day) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return storeTaskService.tasklist(day, jeeUserDetails);
    }

    /**
     * 打卡
     */
    @ApiOperation("打卡")
    @RequestMapping(value = "/punchTheClock", method = RequestMethod.POST)
    public ApiRes punchTheClock(@RequestBody PunchTheClockDTO punchTheClockDTO) {
        return storeTaskService.punchTheClock(punchTheClockDTO);
    }

    /**
     * 操作任务
     */
    @ApiOperation("操作任务")
    @RequestMapping(value = "/updatetask", method = RequestMethod.POST)
    public ApiRes updatetask(@RequestBody UpdatetaskDTO updatetaskDTO) {
        return storeTaskService.updatetask(updatetaskDTO);
    }

}
