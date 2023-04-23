package com.baimeng.bmmerchant.ctrl;

import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.model.dto.ManageStaffListDTO;
import com.baimeng.bmmerchant.model.dto.StaffDTO;
import com.baimeng.bmmerchant.model.vo.QueryClockInfoVO;
import com.baimeng.bmmerchant.model.vo.StaffVO;
import com.baimeng.bmmerchant.model.vo.StoreListVO;
import com.baimeng.bmmerchant.model.vo.TaskInfoVO;
import com.baimeng.bmmerchant.service.ManageTaskService;
import com.baimeng.bmmerchant.service.WorkAttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "考勤模块")
@RestController
@RequestMapping("/workAttendance")
@Slf4j
public class WorkAttendanceController extends CommonCtrl{

    @Resource
    WorkAttendanceService workAttendanceService;
    @Resource
    ManageTaskService manageTaskService;
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
    /**
     * 员工考勤列表
     */
    @ApiOperation("员工考勤列表")
    @RequestMapping(value = "/staffList", method = RequestMethod.GET)
    public ApiRes<StaffVO> staffList(@ApiParam(name = "day", value = "时间", required = true) @NonNull @RequestParam("day") String day) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return workAttendanceService.staffList(day, jeeUserDetails);
    }



    /**
     * 考勤
     */
    @ApiOperation("考勤")
    @RequestMapping(value = "/staff", method = RequestMethod.POST)
    public ApiRes staff(@RequestBody StaffDTO staffDTO) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return workAttendanceService.staff(jeeUserDetails,staffDTO);
    }

    /**
     * 管理员考勤查看列表
     */
    @ApiOperation("管理员考勤查看列表")
    @RequestMapping(value = "/manageStaffList", method = RequestMethod.POST)
    public ApiRes<StaffVO> manageStaffList(@RequestBody ManageStaffListDTO manageStaffListDTO) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return workAttendanceService.manageStaffList( jeeUserDetails,manageStaffListDTO);
    }



    /**
     * 查看考勤打卡
     */
    @ApiOperation("查看考勤打卡")
    @RequestMapping(value = "/queryStaffInfo", method = RequestMethod.GET)
    public ApiRes<QueryClockInfoVO> queryStaffInfo(
            @ApiParam(name = "attendanceConfigId", value = "attendanceConfigId", required = true) @NonNull @RequestParam("attendanceConfigId") String attendanceConfigId
            ,@ApiParam(name = "day", value = "day", required = true) @NonNull @RequestParam("day") String day
            ,@ApiParam(name = "sysUserId", value = "sysUserId", required = true) @NonNull @RequestParam("sysUserId") String sysUserId) {

        return workAttendanceService.queryStaffInfo(attendanceConfigId,day,sysUserId);
    }
}
