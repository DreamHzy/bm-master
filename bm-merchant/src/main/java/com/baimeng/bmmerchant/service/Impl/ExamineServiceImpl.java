package com.baimeng.bmmerchant.service.Impl;

import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.comment.PageData;
import com.baimeng.bmmerchant.comment.PageWrap;
import com.baimeng.bmmerchant.enums.ApplyTypeMsg;
import com.baimeng.bmmerchant.enums.ExamineStatusMsg;
import com.baimeng.bmmerchant.mapper.ExamineMapper;
import com.baimeng.bmmerchant.model.dto.ExamineAdministratorsListDTO;
import com.baimeng.bmmerchant.model.dto.ExamineShopownerListDTO;
import com.baimeng.bmmerchant.model.vo.ExamineAdministratorsListVO;
import com.baimeng.bmmerchant.model.vo.ExamineAdministratorsVO;
import com.baimeng.bmmerchant.model.vo.ExamineShopownerListVO;
import com.baimeng.bmmerchant.model.vo.ExamineShopownerVO;
import com.baimeng.bmmerchant.service.ExamineService;
import com.baimeng.bmservice.impl.*;
import com.baimeng.bmservice.model.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ExamineServiceImpl implements ExamineService {
    @Resource
    IBProdApplyService ibProdApplyService;
    @Resource
    IBProdApplyDetailsService ibProdApplyDetailsService;
    @Resource
    ExamineMapper examineMapper;
    @Resource
    IBProdHistoryService ibProdHistoryService;
    @Resource
    IBProdService ibProdService;
    @Resource
    IBExamineService ibExamineService;
    @Resource
    IBStoreSysUserService ibStoreSysUserService;

    @Override
    public ApiRes examineGoods(Integer prodApplyId, JeeUserDetails jeeUserDetails) {
        BProdApply bProdApply = ibProdApplyService.getById(prodApplyId);
        if (bProdApply == null) {
            return ApiRes.customFail("申请单不存在");
        }
        if (bProdApply.getStatus() == -1) {
            //查询申请单详情
            List<BProdApplyDetails> bProdApplyDetailsList = ibProdApplyDetailsService.list(BProdApplyDetails.gw().eq(BProdApplyDetails::getProdApplyId, prodApplyId));
            //入库则不需要审核直接提交入库
            if (bProdApply.getApplyType() == 2) {//入库
                //查询申请的产品信息
                List<BProd> bProdList = examineMapper.listByProdIds(bProdApplyDetailsList);
                bProdApply.setStatus(4);
                bProdApply.setExamineTime(new Date());
                bProdApply.setCompleteTime(new Date());
                bProdApply.setExamineUserId(jeeUserDetails.getSysUser().getSysUserId());
                List<BProdHistory> bProdHistoryList = new ArrayList<>();
                //操作数据库
                bProdApplyDetailsList.stream().forEach(
                        bProdApplyDetails -> {
                            bProdList.stream().forEach(
                                    bProd -> {
                                        if (bProd.getProdId() == bProdApplyDetails.getProdId()) {
                                            BProdHistory bProdHistory = new BProdHistory();
                                            bProdHistory.setProdId(bProd.getProdId());
                                            bProdHistory.setInventoryDetailsId(bProdApplyDetails.getProdApplyDetailsId());
                                            bProdHistory.setWarehousing(bProd.getWarehousingCount());
                                            bProdHistory.setReceiving(bProd.getCollectCount());
                                            bProdHistory.setQuantity(bProdApplyDetails.getAmount());
                                            bProdHistory.setStock(bProd.getWarehousingCount() + bProdApplyDetails.getAmount()-bProd.getCollectCount());
                                            bProdHistory.setType(1);
                                            bProdHistory.setSysUserId(jeeUserDetails.getSysUser().getSysUserId());
                                            bProdHistory.setCreatedAt(new Date());
                                            bProdHistoryList.add(bProdHistory);
                                            bProd.setWarehousingCount(bProd.getWarehousingCount() + bProdApplyDetails.getAmount());
                                        }
                                    }
                            );
                        }
                );
                ibProdHistoryService.saveBatch(bProdHistoryList);
                ibProdService.updateBatchById(bProdList);
                ibProdApplyService.updateById(bProdApply);
            } else {//出库
                bProdApply.setStatus(0);
                bProdApply.setApplyTime(new Date());
                BExamine bExamine = new BExamine();
                bExamine.setApplyTime(bProdApply.getApplyTime());
                bExamine.setStatus((byte) 0);
                bExamine.setStoreNo(bProdApply.getStoreNo());
                bExamine.setApplyUserId(Integer.valueOf(bProdApply.getSysUserId()));
                bExamine.setOtherId(bProdApply.getProdApplyId());
                bExamine.setType(0);
                ibProdApplyService.updateById(bProdApply);
                ibExamineService.save(bExamine);
            }
        }
        return ApiRes.ok();
    }

    @Override
    public ApiRes<ExamineShopownerVO> examineShopownerList(JeeUserDetails jeeUserDetails, PageWrap<ExamineShopownerListDTO> pageWrap) {
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);
        ExamineShopownerListDTO examineShopownerListDTO = pageWrap.getModel();
        PageHelper.startPage(pageWrap.getPage(), pageWrap.getCapacity());
        List<ExamineShopownerListVO> examineShopownerListVOList = examineMapper.examineShopownerList(bSysUser.getSysUserId(), bStoreSysUser.getStoreNo(), examineShopownerListDTO.getStatus());
        PageData<ExamineShopownerListVO> from = PageData.from(new PageInfo<>(examineShopownerListVOList));
        from.getRecords().stream().forEach(
                examineShopownerListVO -> {
                    examineShopownerListVO.setStatusMag(ExamineStatusMsg.getStatusDesc(examineShopownerListVO.getStatus()));
                    examineShopownerListVO.setApplyTypeMsg(ApplyTypeMsg.getStatusDesc(examineShopownerListVO.getApplyType()));
                }
        );
        //查询待审核数据
        Integer count = examineMapper.queryCountShopwner(bSysUser.getSysUserId(), bStoreSysUser.getStoreNo(), 0);
        //审核通过数据
        Integer passAuditNum = examineMapper.queryCountShopwner(bSysUser.getSysUserId(), bStoreSysUser.getStoreNo(), 1);
        ExamineShopownerVO examineShopownerVO = new ExamineShopownerVO();
        examineShopownerVO.setExamineShopownerListVOList(from);
        examineShopownerVO.setPassAuditNum(passAuditNum + "");
        examineShopownerVO.setWaitAuditNum(count + "");
        return ApiRes.ok(examineShopownerVO);

    }

    @Override
    public ApiRes<ExamineAdministratorsVO> examineAdministratorsList(JeeUserDetails jeeUserDetails, PageWrap<ExamineAdministratorsListDTO> pageWrap) {
        BSysUser sysUser = jeeUserDetails.getSysUser();
        ExamineAdministratorsListDTO examineAdministratorsListDTO = pageWrap.getModel();
        List<String> storeNumberList = new ArrayList<>();
        if (Objects.equals(examineAdministratorsListDTO.getStoreNo(), "")) {
            storeNumberList = examineMapper.selectSysUser(sysUser.getSysUserId());
        } else {
            storeNumberList.add(examineAdministratorsListDTO.getStoreNo());
        }
        PageHelper.startPage(pageWrap.getPage(), pageWrap.getCapacity());
        List<ExamineAdministratorsListVO> examineAdministratorsListVOList = examineMapper.examineAdministratorsList(storeNumberList, examineAdministratorsListDTO.getStatus());
        PageData<ExamineAdministratorsListVO> from = PageData.from(new PageInfo<>(examineAdministratorsListVOList));
        from.getRecords().stream().forEach(
                examineAdministratorsListVO -> {
                    examineAdministratorsListVO.setStatusMag(ExamineStatusMsg.getStatusDesc(examineAdministratorsListVO.getStatus()));
                    examineAdministratorsListVO.setApplyTypeMsg(ApplyTypeMsg.getStatusDesc(examineAdministratorsListVO.getApplyType()));
                }
        );
        ExamineAdministratorsVO examineAdministratorsVO = new ExamineAdministratorsVO();
        examineAdministratorsVO.setExamineAdministratorsListVOPageData(from);
        Integer count = examineMapper.queryCountAdmin(storeNumberList);
        examineAdministratorsVO.setWaitAuditNum(count + "");
        return ApiRes.ok(examineAdministratorsVO);
    }

    @Override
    public ApiRes examinePass(JeeUserDetails jeeUserDetails, Integer prodApplyId) {
        BSysUser sysUser = jeeUserDetails.getSysUser();
        BProdApply bProdApply = ibProdApplyService.getById(prodApplyId);
        if (bProdApply == null) {
            return ApiRes.customFail("申请单不存在");
        }
        if (bProdApply.getStatus() != 0) {
            return ApiRes.customFail("申请单状态不正确");
        }
        BExamine examine = ibExamineService.getOne(BExamine.gw().eq(BExamine::getOtherId, bProdApply.getProdApplyId()));
        examine.setStatus((byte) 1);
        examine.setExamineTime(new Date());

        bProdApply.setStatus(1);
        bProdApply.setExamineTime(new Date());
        bProdApply.setExamineUserId(sysUser.getSysUserId());
        ibProdApplyService.updateById(bProdApply);
        ibExamineService.updateById(examine);
        return ApiRes.ok();
    }

    @Override
    public ApiRes shopownePass(JeeUserDetails jeeUserDetails, Integer prodApplyId) {
        BSysUser sysUser = jeeUserDetails.getSysUser();
        BProdApply bProdApply = ibProdApplyService.getById(prodApplyId);
        if (bProdApply == null) {
            return ApiRes.customFail("申请单不存在");
        }
        if (bProdApply.getStatus() != 1) {
            return ApiRes.customFail("申请单状态不正确");
        }
        //查询申请单详情
        List<BProdApplyDetails> bProdApplyDetailsList = ibProdApplyDetailsService.list(BProdApplyDetails.gw().eq(BProdApplyDetails::getProdApplyId, prodApplyId));
        //查询申请的产品信息
        List<BProd> bProdList = examineMapper.listByProdIds(bProdApplyDetailsList);
        bProdApply.setStatus(4);
        bProdApply.setExamineTime(new Date());
        bProdApply.setCompleteTime(new Date());
        bProdApply.setExamineUserId(jeeUserDetails.getSysUser().getSysUserId());
        List<BProdHistory> bProdHistoryList = new ArrayList<>();
        //操作数据库
        bProdApplyDetailsList.stream().forEach(
                bProdApplyDetails -> {
                    bProdList.stream().forEach(
                            bProd -> {
                                if (bProd.getProdId() == bProdApplyDetails.getProdId()) {
                                    BProdHistory bProdHistory = new BProdHistory();
                                    bProdHistory.setProdId(bProd.getProdId());
                                    bProdHistory.setInventoryDetailsId(bProdApplyDetails.getProdApplyDetailsId());
                                    bProdHistory.setWarehousing(bProd.getWarehousingCount());
                                    bProdHistory.setReceiving(bProd.getCollectCount());
                                    bProdHistory.setQuantity(bProdApplyDetails.getAmount());
                                    bProdHistory.setType(2);
                                    bProdHistory.setStock(bProd.getWarehousingCount() - bProd.getCollectCount() - bProdApplyDetails.getAmount());
                                    bProdHistory.setSysUserId(jeeUserDetails.getSysUser().getSysUserId());
                                    bProdHistory.setCreatedAt(new Date());
                                    bProdHistoryList.add(bProdHistory);
                                    bProd.setCollectCount(bProd.getCollectCount() + bProdApplyDetails.getAmount());
                                }
                            }
                    );
                }
        );
        BExamine examine = ibExamineService.getOne(BExamine.gw().eq(BExamine::getOtherId, bProdApply.getProdApplyId()));
        examine.setStatus((byte) 4);
        ibExamineService.updateById(examine);
        ibProdHistoryService.saveBatch(bProdHistoryList);
        ibProdService.updateBatchById(bProdList);
        ibProdApplyService.updateById(bProdApply);
        return ApiRes.ok();
    }


}
