package com.baimeng.bmmerchant.service.Impl;

import com.baimeng.bmcomponentsoss.config.AliyunOssYmlConfig;
import com.baimeng.bmcore.constants.CS;
import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.enums.StaffDetailEnum;
import com.baimeng.bmmerchant.mapper.ManageTaskMapper;
import com.baimeng.bmmerchant.model.dto.ManageStaffListDTO;
import com.baimeng.bmmerchant.model.dto.StaffDTO;
import com.baimeng.bmmerchant.model.vo.QueryClockInfoVO;
import com.baimeng.bmmerchant.model.vo.StaffDetailVO;
import com.baimeng.bmmerchant.model.vo.StaffUserIdDTO;
import com.baimeng.bmmerchant.model.vo.StaffVO;
import com.baimeng.bmmerchant.service.WorkAttendanceService;
import com.baimeng.bmservice.impl.*;
import com.baimeng.bmservice.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class WorkAttendanceServiceImpl implements WorkAttendanceService {
    @Resource
    IBStoreSysUserService ibStoreSysUserService;
    @Resource
    IBCheckWorkAttendanceConfigService ibCheckWorkAttendanceConfigService;
    @Resource
    IBCheckWorkAttendanceDeatilService ibCheckWorkAttendanceDeatilService;
    @Resource
    IBFileService ibFileService;
    @Resource
    ManageTaskMapper manageTaskMapper;
    @Resource
    IBSysUserService ibSysUserService;
    @Resource
    AliyunOssYmlConfig aliyunOssYmlConfig;

    @Override
    public ApiRes<StaffVO> staffList(String day, JeeUserDetails jeeUserDetails) {

        StaffVO staffVO = new StaffVO();
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        staffVO.setUserName(bSysUser.getRealname());
        staffVO.setUserNo(bSysUser.getUserNo());
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);
        //查询用户相关打卡信息
        List<StaffDetailVO> staffDetailVOS = manageTaskMapper.staffDetailVOS(day, bStoreSysUser.getStoreNo(), bStoreSysUser.getSysUserId());
        staffDetailVOS.stream().forEach(
                staffDetailVO -> {
                    if(staffDetailVO.getState()==null){
                        staffDetailVO.setState("0");
                    }
                    staffDetailVO.setSysUserId(bStoreSysUser.getSysUserId()+"");
                    staffDetailVO.setStateMsg(StaffDetailEnum.getStatusDesc(staffDetailVO.getState()));
                    staffDetailVO.setIcon(aliyunOssYmlConfig.getUrl()+staffDetailVO.getIcon());
                    staffDetailVO.setUrl(aliyunOssYmlConfig.getUrl()+staffDetailVO.getUrl());
                }
        );
        staffVO.setStaffDetailVOS(staffDetailVOS);
        return ApiRes.ok(staffVO);
    }

    @Override
    public ApiRes staff(JeeUserDetails jeeUserDetails, StaffDTO staffDTO) {
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);

        String attendanceConfigId = staffDTO.getAttendanceConfigId();
        if (StringUtils.isAnyBlank(attendanceConfigId)) {
            return ApiRes.customFail("请输入检查内容attendanceConfigId");
        }
        String address = staffDTO.getAddress();
        if (StringUtils.isAnyBlank(address)) {
            return ApiRes.customFail("请输入检查内容address");
        }
        String image = staffDTO.getImage();
        if (StringUtils.isAnyBlank(image)) {
            return ApiRes.customFail("请输入检查内容image");
        }

        BCheckWorkAttendanceConfig bCheckWorkAttendanceConfig = ibCheckWorkAttendanceConfigService.getById(attendanceConfigId);
        if (bCheckWorkAttendanceConfig == null) {
            return ApiRes.customFail("考勤信息不存在");
        }
        BCheckWorkAttendanceDeatil bCheckWorkAttendanceDeatil = new BCheckWorkAttendanceDeatil();
        bCheckWorkAttendanceDeatil.setAttendanceConfigId(Integer.valueOf(attendanceConfigId));
        bCheckWorkAttendanceDeatil.setAddress(address);
        bCheckWorkAttendanceDeatil.setCreatedAt(new Date());
        bCheckWorkAttendanceDeatil.setCreatedDay(new Date());
        bCheckWorkAttendanceDeatil.setStoreNo(bStoreSysUser.getStoreNo());
        bCheckWorkAttendanceDeatil.setSysUserId(bStoreSysUser.getSysUserId());
        String endDate = new SimpleDateFormat("HH:mm").format(new Date());
        String startTime = "";
        int resultTime = 0;
        if (bCheckWorkAttendanceConfig.getStartTime() != null) {//上班卡
            startTime = new SimpleDateFormat("HH:mm").format(bCheckWorkAttendanceConfig.getStartTime());
            resultTime = endDate.compareTo(startTime);
            if (resultTime > 0) {
                bCheckWorkAttendanceDeatil.setState(CS.TWO);
            } else {
                bCheckWorkAttendanceDeatil.setState(CS.YES);
            }
        } else {//下班卡
            startTime = new SimpleDateFormat("HH:mm").format(bCheckWorkAttendanceConfig.getEndTime());
            resultTime = endDate.compareTo(startTime);
            if (resultTime < 0) {
                bCheckWorkAttendanceDeatil.setState(CS.THREE);
            } else {
                bCheckWorkAttendanceDeatil.setState(CS.YES);
            }
        }
        if (!ibCheckWorkAttendanceDeatilService.save(bCheckWorkAttendanceDeatil)) {
            return ApiRes.customFail("保存打卡信息错误");
        }
        BFile bFile = new BFile();
        bFile.setBelongInfoId(bCheckWorkAttendanceDeatil.getAttendanceDeatilId() + "");
        bFile.setBelongInfo("b_check_work_attendance_deatil");
        bFile.setType(CS.FOUR_INT);
        bFile.setUrl(staffDTO.getImage());
        bFile.setState(CS.YES);
        bFile.setCreatedAt(new Date());
        if (!ibFileService.save(bFile)) {
            return ApiRes.customFail("图片存储失败");
        }
        return ApiRes.ok();
    }

    @Override
    public ApiRes<StaffVO> manageStaffList(JeeUserDetails jeeUserDetails, ManageStaffListDTO manageStaffListDTO) {
        String storeNo = manageStaffListDTO.getStoreNo();
        log.info("storeNo={}",storeNo);
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);
        List<String> stores = new ArrayList<>();
        if (StringUtils.isEmpty(storeNo)) {
            //用户门店下所有的id
            stores = manageTaskMapper.onDutyAmount(bStoreSysUser.getSysUserId());
        } else {
            stores.add(storeNo);
        }
        List<StaffVO> staffVOS = new ArrayList<>();
        //查询所有的员工
        List<StaffUserIdDTO> staffUserID = manageTaskMapper.staffUserID(stores);

        //查询用户考勤信息
        staffUserID.stream().forEach(
                staffUserIdDTO -> {
                    StaffVO staffVO = new StaffVO();
                    //查询用户相关打卡信息
                    BSysUser bSysUserNew = ibSysUserService.getById(staffUserIdDTO.getSysUserId());
                    staffVO.setUserName(bSysUserNew.getRealname());
                    staffVO.setUserNo(bSysUserNew.getUserNo());
                    List<StaffDetailVO> staffDetailVOS = manageTaskMapper.staffDetailVOS(manageStaffListDTO.getDay(), staffUserIdDTO.getStoreNo(), Integer.valueOf(staffUserIdDTO.getSysUserId()));
                    staffDetailVOS.stream().forEach(
                            staffDetailVO -> {
                                if(staffDetailVO.getState()==null){
                                    staffDetailVO.setState("0");
                                }
                                staffDetailVO.setSysUserId(staffUserIdDTO.getSysUserId());
                                staffDetailVO.setStateMsg(StaffDetailEnum.getStatusDesc(staffDetailVO.getState()));
                                staffDetailVO.setIcon(aliyunOssYmlConfig.getUrl()+staffDetailVO.getIcon());
                                staffDetailVO.setUrl(aliyunOssYmlConfig.getUrl()+staffDetailVO.getUrl());
                            }
                    );
                    staffVO.setStaffDetailVOS(staffDetailVOS);
                    staffVOS.add(staffVO);
                }
        );

        return ApiRes.ok(staffVOS);
    }

    @Override
    public ApiRes<QueryClockInfoVO> queryStaffInfo(String attendanceConfigId, String day,String sysUserId) {
        //查看当考勤记录
        BCheckWorkAttendanceDeatil bCheckWorkAttendanceDeatil =
                ibCheckWorkAttendanceDeatilService.getOne(BCheckWorkAttendanceDeatil.gw().eq(BCheckWorkAttendanceDeatil::getAttendanceConfigId,attendanceConfigId)
                        .eq(BCheckWorkAttendanceDeatil::getCreatedDay,day)
                        .eq(BCheckWorkAttendanceDeatil::getSysUserId,sysUserId));
//                        .eq(BCheckWorkAttendanceDeatil::getState,CS.YES));
        if(bCheckWorkAttendanceDeatil==null){
            return ApiRes.customFail("暂无打卡记录");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BFile bFile = ibFileService.list(BFile.gw().eq(BFile::getBelongInfoId, bCheckWorkAttendanceDeatil.getAttendanceDeatilId()).eq(BFile::getBelongInfo, "b_check_work_attendance_deatil")).get(0);
        QueryClockInfoVO queryClockInfoVO = new QueryClockInfoVO();
        queryClockInfoVO.setTime(sdf.format(bCheckWorkAttendanceDeatil.getCreatedAt()));
        queryClockInfoVO.setAddress(bCheckWorkAttendanceDeatil.getAddress());
        queryClockInfoVO.setIamgeUrl(aliyunOssYmlConfig.getUrl() + bFile.getUrl());
        return ApiRes.ok(queryClockInfoVO);
    }

}