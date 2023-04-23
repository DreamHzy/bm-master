package com.baimeng.bmmerchant.service.Impl;

import cn.hutool.http.HttpStatus;
import com.baimeng.bmcomponentsoss.config.AliyunOssYmlConfig;
import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.mapper.InspectMapper;
import com.baimeng.bmmerchant.mapper.InventoryMapper;
import com.baimeng.bmmerchant.model.vo.ApplyIdVO;
import com.baimeng.bmmerchant.model.dto.CategoryNameDTO;
import com.baimeng.bmmerchant.model.dto.*;
import com.baimeng.bmmerchant.model.vo.*;
import com.baimeng.bmmerchant.service.InventoryService;
import com.baimeng.bmservice.impl.BProdApplyDetailsService;
import com.baimeng.bmservice.impl.BProdApplyService;
import com.baimeng.bmservice.impl.BProdService;
import com.baimeng.bmservice.impl.IBStoreSysUserService;
import com.baimeng.bmservice.mapper.BProdApplyDetailsMapper;
import com.baimeng.bmservice.mapper.BProdApplyMapper;
import com.baimeng.bmservice.mapper.BProdMapper;
import com.baimeng.bmservice.model.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Resource
    IBStoreSysUserService ibStoreSysUserService;

    @Resource
    InventoryMapper inventoryMapper;

    @Resource
    InspectMapper inspectMapper;

    @Resource
    AliyunOssYmlConfig aliyunOssYmlConfig;

    @Resource
    BProdService bProdService;

    @Resource
    BProdMapper bProdMapper;

    @Resource
    BProdApplyService bProdApplyService;

    @Resource
    BProdApplyDetailsMapper bProdApplyDetailsMapper;

    @Resource
    BProdApplyMapper bProdApplyMapper;

    @Resource
    BProdApplyDetailsService bProdApplyDetailsService;

    @Override
    public ApiRes<AdminInventoryVO> adminInventory(AdminInventoryDTO inventoryDTO) {

        //商品列表数据
        List<AdminInventoryVO> inventoryVOList = inventoryMapper.selectAdminCategory(inventoryDTO.getStoreNo(), aliyunOssYmlConfig.getUrl());
        if (inventoryVOList.size() == 0) {
            return ApiRes.ok();
        }
        //商品日均
        List<ProdAverageDTO> list = inventoryMapper.selectTests();
        inventoryVOList.stream().forEach(
                adminInventoryVO -> {
                    adminInventoryVO.setDayAverage("0");
                    list.stream().forEach(
                            prodAverageDTO -> {
                                if (prodAverageDTO.getProdId().equals(adminInventoryVO.getProdId())) {
                                    adminInventoryVO.setDayAverage(prodAverageDTO.getDayAverage());
                                }
                            }
                    );
                }
        );
        return ApiRes.ok(inventoryVOList);
    }

    @Override
    public ApiRes<CategoryProdsVO> search(String prodName) {
        List<CategoryProdsVO> prodDTOList = inventoryMapper.selectName(prodName, aliyunOssYmlConfig.getUrl());
        return ApiRes.ok(prodDTOList);
    }

    @Override
    public ApiRes<DetailsVO> details(DdetailsDTO detailsDTO) {
        AdminInventoryVO adminInventoryVO = inventoryMapper.selectDetails(detailsDTO.getProdId(), aliyunOssYmlConfig.getUrl());
        //商品日均
        String dayAverage = inventoryMapper.selectQuery(detailsDTO.getProdId());
        if (StringUtils.isEmpty(dayAverage)) {
            adminInventoryVO.setDayAverage("0");
        } else {
            adminInventoryVO.setDayAverage(dayAverage);
        }


//        List<ProdAverageDTO> list = inventoryMapper.selectTests();
//        list.stream().forEach(test -> {
//            adminInventoryVO.setDayAverage("0");
//            if (test.getProdId().equals(adminInventoryVO.getProdId())) {
//                adminInventoryVO.setDayAverage(test.getDayAverage());
//            }
//        });
        String createTime = detailsDTO.getCreateTime() + " " + "00:00:00";
        String endTime = detailsDTO.getCreateTime() + " " + "23:59:59";
        List<InventoryDetailsDTO> details = inventoryMapper.selectDetailsList(detailsDTO.getProdId(), createTime, endTime, aliyunOssYmlConfig.getUrl());
        DetailsVO detailsVO = new DetailsVO();
        detailsVO.setAdminInventoryVO(adminInventoryVO);
        detailsVO.setDetails(details);
        return ApiRes.ok(detailsVO);
    }

    @Override
    public ApiRes<CategoryProdVO> enterDepot(AdminInventoryDTO inventoryDTO) {
        return getCategoryProdVOApiRes(inventoryDTO);
    }

    //出入库逻辑一样提取出来
    private ApiRes<CategoryProdVO> getCategoryProdVOApiRes(AdminInventoryDTO inventoryDTO) {
        List<CategoryNameDTO> list = inventoryMapper.selectInventory(inventoryDTO.getStoreNo());
        List<CategoryProdDTO> categoryProdDTOList = inventoryMapper.selectCategoryProd(inventoryDTO.getStoreNo(), aliyunOssYmlConfig.getUrl());
        List<CategoryProdVO> prodVOList = new ArrayList<>();
        list.stream().forEach(
                categoryNameDTO -> {
                    CategoryProdVO categoryProdVO = new CategoryProdVO();
                    List<CategoryProdDTO> categoryProdDTOList1 = new ArrayList<>();
                    categoryProdVO.setProdCategoryId(categoryNameDTO.getProdCategoryId());
                    categoryProdVO.setName(categoryNameDTO.getName());
                    categoryProdDTOList.stream().forEach(
                            categoryProdDTO -> {
                                if (categoryProdVO.getProdCategoryId().equals(categoryProdDTO.getProdCategoryId())) {
                                    categoryProdDTOList1.add(categoryProdDTO);
                                }
                            }
                    );
                    categoryProdVO.setProdDTOList(categoryProdDTOList1);
                    prodVOList.add(categoryProdVO);
                });
        return ApiRes.ok(prodVOList);
    }

    @Override
    public ApiRes<ApplyIdVO> confirm(ConfirmListDTO confirmListDTO, JeeUserDetails jeeUserDetails) {
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);
        List<ConfirmDTO> confirmDTOList = confirmListDTO.getConfirmDTOList();

        Integer c = 0;
        BigDecimal d = BigDecimal.valueOf(0.000);
        for (ConfirmDTO confirmDTO : confirmDTOList) {
            BProd bProd = bProdMapper.selectById(confirmDTO.getProdId());
            //单个商品数量
            Integer a = 0;
            //单个商品总价
            BigDecimal b;
            a = a + Integer.valueOf(confirmDTO.getProdAmount());
            b = bProd.getPrice().multiply(BigDecimal.valueOf(a));
            c = c + a;
            d = d.add(b);
        }
        //申请表数据
        BProdApply bProdApply = new BProdApply();
        //申请时间
        bProdApply.setApplyTime(new Date());
        //申请门店编号
        bProdApply.setStoreNo(bStoreSysUser.getStoreNo());
        //申请人
        bProdApply.setSysUserId(bStoreSysUser.getSysUserId().toString());
        //申请类型
        bProdApply.setApplyType(Integer.valueOf(confirmListDTO.getApplyType()));
        //商品数量
        bProdApply.setProdAmount(c);
        //商品总额
        bProdApply.setPriceTotalAmount(d);
        //状态
        bProdApply.setStatus(-1);
        if (!bProdApplyService.save(bProdApply)) {
            return ApiRes.customFail("保存失败");
        }
        //查询申请的商品是不是有正在出库审核的单子，如果有就需要对库存做限制

        List<BProdApplyDetails> bProdApplyDetails = new ArrayList<>();
        Integer num = 0;
        for (ConfirmDTO confirmDTO : confirmDTOList) {
            BProd bProd = bProdMapper.selectById(confirmDTO.getProdId());
            ProdWriteStockVO prodWriteStockVO = inventoryMapper.prodWriteStockVOS(bProd.getProdId() + "");
            if (prodWriteStockVO != null) {
                num = bProd.getWarehousingCount() - bProd.getCollectCount() - Integer.valueOf(confirmDTO.getProdAmount()) - Integer.valueOf(prodWriteStockVO.getAmount());
                if (num < 0) {
                    return ApiRes.customFail(bProd.getProdName() + "库存不足");
                }
            }
            BProdApplyDetails s = new BProdApplyDetails();
            s.setAmount(Integer.valueOf(confirmDTO.getProdAmount()));
            s.setProdId(Integer.valueOf(confirmDTO.getProdId()));
            s.setCreateTime(new Date());
            s.setPrice(bProd.getPrice());
            s.setProdApplyId(bProdApply.getProdApplyId());
            bProdApplyDetails.add(s);
        }
        if (!bProdApplyDetailsService.saveBatch(bProdApplyDetails)) {
            return ApiRes.customFail("保存失败");
        }

        ApplyIdVO applyIdVO = new ApplyIdVO();
        applyIdVO.setProdApplyId(bProdApply.getProdApplyId());
        return ApiRes.ok(applyIdVO);
    }

    @Override
    public ApiRes<ConfirmShowVO> confirmShow(Integer prodApplyId) {
        //订单信息
        ConfirmShowTwoDTO inventoryDetailsDTO = inventoryMapper.selectProdApply(prodApplyId);
        //商品列表
        List<ConfirmListTwoDTO> confirmDTOList = inventoryMapper.selectConfirmList(prodApplyId, aliyunOssYmlConfig.getUrl());
        List<ConfirmListTwoDTO> confirmDTOTwoList = new ArrayList<>();
        for (ConfirmListTwoDTO confirmListTwoDTO : confirmDTOList) {
            if (confirmListTwoDTO.getProdAmount() <= 0) {
                continue;
            }
            confirmDTOTwoList.add(confirmListTwoDTO);
        }
        Integer c = 0;
        BigDecimal d = BigDecimal.valueOf(0.000);
        for (ConfirmListTwoDTO confirmDTO : confirmDTOTwoList) {
            //单个商品数量
            Integer a = 0;
            //单个商品总价
            BigDecimal b;
            a = a + confirmDTO.getProdAmount();
            b = confirmDTO.getPrice().multiply(BigDecimal.valueOf(a));
            c = c + a;
            d = d.add(b);
        }
        inventoryDetailsDTO.setQuantity(c.toString());
        inventoryDetailsDTO.setPrice(d.toString());
        ConfirmShowVO confirmShowVO = new ConfirmShowVO();
        confirmShowVO.setInventoryDetailsDTO(inventoryDetailsDTO);
        confirmShowVO.setConfirmDTOList(confirmDTOTwoList);
        return ApiRes.ok(confirmShowVO);
    }

    @Override
    public ApiRes updateApply(UpdateApplyDTO applyDTO) {
        BProdApplyDetails bProdApplyDetails1 = bProdApplyDetailsMapper.selectById(applyDTO.getProdApplyDetailsId());

        if (bProdApplyDetails1 == null) {
            return ApiRes.customFail("id不存在");
        }
        BProdApply bProdApply = bProdApplyMapper.selectById(bProdApplyDetails1.getProdApplyId());
        if (!bProdApply.getStatus().equals(-1) && !bProdApply.getStatus().equals(0)) {
            return ApiRes.ok("订单状态只能是-1选购中 0审核中");
        }
        if (Integer.valueOf(applyDTO.getProdAmount()) < 1) {
            bProdApplyDetailsService.removeById(applyDTO.getProdApplyDetailsId());
        } else {
            BProdApplyDetails bProdApplyDetails = new BProdApplyDetails();
            bProdApplyDetails.setProdApplyDetailsId(Integer.valueOf(applyDTO.getProdApplyDetailsId()));
            bProdApplyDetails.setAmount(Integer.valueOf(applyDTO.getProdAmount()));
            if (!bProdApplyDetailsService.updateById(bProdApplyDetails)) {
                return ApiRes.customFail("修改失败");
            }
        }
        return ApiRes.ok(HttpStatus.HTTP_OK);
    }

    @Override
    public ApiRes addProd(AddProdDTO applyDTO) {
        //先查询该笔订单是否有这个商品，如果有则不做处理

        BProdApplyDetails bProdApplyDetails =
                bProdApplyDetailsMapper.selectOne(BProdApplyDetails.gw().eq(BProdApplyDetails::getProdApplyId, applyDTO.getProdApplyId())
                        .eq(BProdApplyDetails::getProdId, applyDTO.getProdId()));
        if (bProdApplyDetails == null) {
            bProdApplyDetails = new BProdApplyDetails();
            BProd bProd = bProdMapper.selectById(applyDTO.getProdId());
            bProdApplyDetails.setProdApplyId(Integer.valueOf(applyDTO.getProdApplyId()));
            bProdApplyDetails.setProdId(Integer.valueOf(applyDTO.getProdId()));
            bProdApplyDetails.setAmount(1);
            bProdApplyDetails.setPrice(bProd.getPrice());
            bProdApplyDetails.setCreateTime(new Date());
            if (!bProdApplyDetailsService.save(bProdApplyDetails)) {
                return ApiRes.customFail("添加失败");
            }
        }
        return ApiRes.ok(HttpStatus.HTTP_OK);
    }

    @Override
    public ApiRes<ChooseProdVO> chooseProd(ChooseProdDTO chooseProdDTO) {
        List<Integer> integerList = inventoryMapper.selectNotInId(chooseProdDTO.getApplyId());
        List<ChooseProdVO> prodVOList = inventoryMapper.selectChooseProd(integerList, chooseProdDTO.getProdName());
        return ApiRes.ok(prodVOList);
    }
}