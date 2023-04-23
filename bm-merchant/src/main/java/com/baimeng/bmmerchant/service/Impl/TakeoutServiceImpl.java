package com.baimeng.bmmerchant.service.Impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.comment.PageData;
import com.baimeng.bmmerchant.comment.PageWrap;
import com.baimeng.bmmerchant.mapper.TakeoutMapper;
import com.baimeng.bmmerchant.model.dto.*;
import com.baimeng.bmmerchant.model.vo.TakeoutItemVO;
import com.baimeng.bmmerchant.model.vo.VisitDetailsVO;
import com.baimeng.bmmerchant.model.vo.VisitListVO;
import com.baimeng.bmmerchant.service.TakeoutService;
import com.baimeng.bmservice.impl.BNewTakeoutService;
import com.baimeng.bmservice.impl.IBStoreSysUserService;
import com.baimeng.bmservice.model.BNewTakeout;
import com.baimeng.bmservice.model.BStoreSysUser;
import com.baimeng.bmservice.model.BSysUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class TakeoutServiceImpl implements TakeoutService {

    @Resource
    BNewTakeoutService bNewTakeoutService;
    @Resource
    TakeoutMapper takeoutMapper;
    @Resource
    IBStoreSysUserService ibStoreSysUserService;

    @Override
    public ApiRes countTakeout(TakeoutDTO takeoutDTO, JeeUserDetails jeeUserDetails) {
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);

        if (Objects.isNull(takeoutDTO)) {
            ApiRes.customFail("请输入数据");
        }
        BNewTakeout newTakeout = new BNewTakeout();
        newTakeout.setStoreNo(bStoreSysUser.getStoreNo());
        newTakeout.setSysUserId(bStoreSysUser.getSysUserId().toString());
        DateTime parse = DateUtil.parse(takeoutDTO.getTakeoutDate());
        newTakeout.setTakeoutDate(parse);
        Integer integer = takeoutMapper.selectMax(takeoutDTO.getTakeoutDate(), takeoutDTO.getTakeoutPlatform(), bStoreSysUser.getStoreNo());
        if (integer == null) {
            integer = 0;
        }
        integer += 1;
        newTakeout.setNumber(integer);
        newTakeout.setWeightMultiple(takeoutDTO.getWeightMultiple());
        newTakeout.setAbnormalMultiple(takeoutDTO.getAbnormalMultiple());
        newTakeout.setTasteMultiple(takeoutDTO.getTasteMultiple());
        newTakeout.setPhoneDisgust(takeoutDTO.getPhoneDisgust());
        newTakeout.setGoodIntentions(takeoutDTO.getGoodIntentions());

        newTakeout.setAddWechat(takeoutDTO.getAddWechat());
        newTakeout.setTakeoutFeedback(takeoutDTO.getTakeoutFeedback());
        newTakeout.setReturnMoney(takeoutDTO.getReturnMoney());
        newTakeout.setTakeoutPlatform(takeoutDTO.getTakeoutPlatform());
        newTakeout.setOmission(takeoutDTO.getOmission());
        newTakeout.setCreateAt(new Date());
        newTakeout.setCreateDay(new Date());
        newTakeout.setTakeoutDate(new DateTime());
        if (!bNewTakeoutService.save(newTakeout)) {
            return ApiRes.customFail("保存失败");
        }
        return ApiRes.ok("保存成功");
    }

    @Override
    public ApiRes<VisitListVO> visitListSerive(VisitListDTO visitListDTO, JeeUserDetails jeeUserDetails) {
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);

        VisitListVO visitListVO = takeoutMapper.selectVisitList(visitListDTO.getTakeoutDate(), bStoreSysUser.getStoreNo());
        if (visitListVO.getRebateSum() == null) {
            String a = "0.00";
            visitListVO.setRebateSum(a);
        }
        return ApiRes.ok(visitListVO);
    }

    @Override
    public ApiRes<VisitDetailsVO> takeoutVisitDetailsService(PageWrap<VisitDetailsDTO> pageWrap, JeeUserDetails jeeUserDetails) {
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);

        VisitDetailsDTO model = pageWrap.getModel();

        PageHelper.startPage(pageWrap.getPage(), pageWrap.getCapacity());
        List<VisitDetailsListDTO> visitDetailsListDTOS = takeoutMapper.selectVisitDetails(model.getTakeoutPlatform(), bStoreSysUser.getStoreNo(), model.getDate());
        String sum = takeoutMapper.selectSum(model.getTakeoutPlatform(), bStoreSysUser.getStoreNo(), model.getDate());
        if (sum == null) {
            sum = "0.00";
        }
        PageData<VisitDetailsListDTO> from = PageData.from(new PageInfo<>(visitDetailsListDTOS));
        VisitDetailsVO visitDetailsVO = new VisitDetailsVO();
        visitDetailsVO.setDetails(from);

        visitDetailsVO.setRebateSum(sum);
        return ApiRes.ok(visitDetailsVO);
    }

    @Override
    public ApiRes<TakeoutItemVO> takeoutVisitItemService(VisitItemDTO visitItemDTO) {

        TakeoutVisitItemDTO takeoutItemDTO = takeoutMapper.selectItem(visitItemDTO.getNewtakeoutId());
        TakeoutItemVO takeoutItemVO = new TakeoutItemVO();
        takeoutItemVO.setOmission(takeoutItemDTO.getOmission());
        takeoutItemVO.setTasteMultiple(takeoutItemDTO.getTasteMultiple());
        takeoutItemVO.setWeightMultiple(takeoutItemDTO.getWeightMultiple());
        takeoutItemVO.setAbnormalMultiple(takeoutItemDTO.getAbnormalMultiple());
        takeoutItemVO.setNewTakeoutId(takeoutItemDTO.getNewTakeoutId());
        takeoutItemVO.setStoreNo(takeoutItemDTO.getStoreNo());
        takeoutItemVO.setSysUserId(takeoutItemDTO.getSysUserId());
        takeoutItemVO.setTakeoutDate(takeoutItemDTO.getTakeoutDate());
        takeoutItemVO.setNumber(takeoutItemDTO.getNumber());
        takeoutItemVO.setTakeoutPlatform(takeoutItemDTO.getTakeoutPlatform());
        takeoutItemVO.setPhoneDisgust(takeoutItemDTO.getPhoneDisgust());
        takeoutItemVO.setGoodIntentions(takeoutItemDTO.getGoodIntentions());
        takeoutItemVO.setAddWechat(takeoutItemDTO.getAddWechat());
        takeoutItemVO.setTakeoutFeedback(takeoutItemDTO.getTakeoutFeedback());
        takeoutItemVO.setReturnMoney(takeoutItemDTO.getReturnMoney());

        return ApiRes.ok(takeoutItemVO);
    }

    @Override
    public ApiRes<Integer> number(JeeUserDetails jeeUserDetails, NumberDTO numberDTO) {
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);
        Integer integer = takeoutMapper.selectMax(numberDTO.getTakeoutDate(), numberDTO.getTakeoutPlatform(), bStoreSysUser.getStoreNo());
        if (integer == null) {
            integer = 0;
        }
        integer += 1;
        return ApiRes.ok(integer);
    }

    @Override
    public ApiRes<VisitListVO> adminTakeout(JeeUserDetails jeeUserDetails, AdminDTO adminDTO) {
        //如果没有传门店编号就查询出这个用户下所有的门店
        if (Objects.equals(adminDTO.getStoreNo(), "")) {
            BSysUser sysUser = jeeUserDetails.getSysUser();
            //拿到所有门店编号
            List<String> storeNumberList = takeoutMapper.selectSysUser(sysUser.getSysUserId());
            //外卖统计
            VisitListVO visitListVO = takeoutMapper.selectAdminSum(storeNumberList, adminDTO.getDate());
            if (visitListVO.getRebateSum() == null) {
                visitListVO.setRebateSum("0");
            }
            return ApiRes.ok(visitListVO);
        } else {
            VisitListVO visitListVO = takeoutMapper.selectVisitList(adminDTO.getDate(), adminDTO.getStoreNo());
            if (visitListVO.getRebateSum() == null) {
                visitListVO.setRebateSum("0");
            }
            return ApiRes.ok(visitListVO);
        }
    }

    @Override
    public ApiRes<VisitListVO> admintakeoutDetails(JeeUserDetails jeeUserDetails, PageWrap<AdminDetailsDTO> pageWrap) {
        AdminDetailsDTO model = pageWrap.getModel();
        BSysUser sysUser = jeeUserDetails.getSysUser();
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, sysUser.getSysUserId())).get(0);
        if (Objects.equals(model.getStoreNo(), "")) {

            VisitDetailsVO visitDetailsVO = new VisitDetailsVO();
            //门店编号
            List<String> storeNumberList = takeoutMapper.selectSysUser(sysUser.getSysUserId());
            PageHelper.startPage(pageWrap.getPage(), pageWrap.getCapacity());
            //详情列表
            List<VisitDetailsListDTO> visitDetailsListDTOS = takeoutMapper.selectAdminDetails(model.getDate(), model.getTakeoutPlatform(), storeNumberList);
            PageData<VisitDetailsListDTO> from = PageData.from(new PageInfo<>(visitDetailsListDTOS));
            //总金额
            String amount = takeoutMapper.selectAdminAmount(model.getDate(), model.getTakeoutPlatform(), storeNumberList);
            if (amount == null) {
                amount = "0.00";
            }
            visitDetailsVO.setRebateSum(amount);
            visitDetailsVO.setDetails(from);
            return ApiRes.ok(visitDetailsVO);
        } else {
            PageHelper.startPage(pageWrap.getPage(), pageWrap.getCapacity());
            List<VisitDetailsListDTO> visitDetailsListDTOS = takeoutMapper.selectVisitDetails(model.getTakeoutPlatform(), model.getStoreNo(), model.getDate());
            String sum = takeoutMapper.selectSum(model.getTakeoutPlatform(), bStoreSysUser.getStoreNo(), model.getDate());
            if (sum == null) {
                sum = "0.00";
            }
            PageData<VisitDetailsListDTO> from = PageData.from(new PageInfo<>(visitDetailsListDTOS));
            VisitDetailsVO visitDetailsVO = new VisitDetailsVO();
            visitDetailsVO.setDetails(from);
            visitDetailsVO.setRebateSum(sum);
            return ApiRes.ok(visitDetailsVO);
        }
    }
}
