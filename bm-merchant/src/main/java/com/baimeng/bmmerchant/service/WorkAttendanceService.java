package com.baimeng.bmmerchant.service;

import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.model.dto.ManageStaffListDTO;
import com.baimeng.bmmerchant.model.dto.StaffDTO;
import com.baimeng.bmmerchant.model.vo.QueryClockInfoVO;
import com.baimeng.bmmerchant.model.vo.StaffVO;
import com.baimeng.bmmerchant.model.vo.TaskInfoVO;

public interface WorkAttendanceService {
    ApiRes<StaffVO> staffList(String day, JeeUserDetails jeeUserDetails);

    ApiRes staff(JeeUserDetails jeeUserDetails, StaffDTO staffDTO);

    ApiRes<StaffVO> manageStaffList( JeeUserDetails jeeUserDetails, ManageStaffListDTO manageStaffListDTO);

    ApiRes<QueryClockInfoVO> queryStaffInfo(String attendanceConfigId, String day,String sysUserId);
}
