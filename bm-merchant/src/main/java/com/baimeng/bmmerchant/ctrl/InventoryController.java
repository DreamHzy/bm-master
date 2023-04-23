package com.baimeng.bmmerchant.ctrl;

import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.model.vo.ApplyIdVO;
import com.baimeng.bmmerchant.model.dto.*;
import com.baimeng.bmmerchant.model.vo.*;
import com.baimeng.bmmerchant.service.InventoryService;
import com.baimeng.bmservice.impl.IBStoreSysUserService;
import com.baimeng.bmservice.mapper.BStoreMapper;
import com.baimeng.bmservice.mapper.BStoreSysUserMapper;
import com.baimeng.bmservice.model.BStore;
import com.baimeng.bmservice.model.BStoreSysUser;
import com.baimeng.bmservice.model.BSysUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Store;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "商品库存相关接口")
@RestController
@RequestMapping("/inventory")
@Slf4j
public class InventoryController extends CommonCtrl {

    @Resource
    InventoryService inventoryService;

    @Resource
    IBStoreSysUserService ibStoreSysUserService;

    @ApiOperation("管理员-库存管理")
    @PostMapping("/adminInventory")
    public ApiRes<AdminInventoryVO> adminInventory(@RequestBody AdminInventoryDTO inventoryDTO) {
        return inventoryService.adminInventory(inventoryDTO);
    }

    @ApiOperation("店长-库存管理")
    @PostMapping("/storeManager")
    public ApiRes<AdminInventoryVO> storeManager() {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);
        AdminInventoryDTO adminInventoryDTO = new AdminInventoryDTO();
        adminInventoryDTO.setStoreNo(bStoreSysUser.getStoreNo());
        return inventoryService.adminInventory(adminInventoryDTO);
    }

    @ApiOperation("商品列表")
    @PostMapping("/enterDepot")
    public ApiRes<CategoryProdVO> enterDepot(@RequestBody AdminInventoryDTO inventoryDTO) {
        if (inventoryDTO.getStoreNo()==null||inventoryDTO.getStoreNo().equals("")){
            ///当前用户信息
            JeeUserDetails jeeUserDetails = getCurrentUser();
            BSysUser bSysUser = jeeUserDetails.getSysUser();
            //查询用户门店信息
            BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);
            inventoryDTO.setStoreNo(bStoreSysUser.getStoreNo());
        }
        return inventoryService.enterDepot(inventoryDTO);
    }

    @ApiOperation("商品搜索")
    @GetMapping("/search")
    public ApiRes<CategoryProdsVO> search(@ApiParam(value = "商品名称") @RequestParam(value = "prodName") String prodName) {
        return inventoryService.search(prodName);
    }

    @ApiOperation("物品出入库-详情")
    @PostMapping("/details")
    public ApiRes<DetailsVO> details(@RequestBody DdetailsDTO detailsDTO) {
        return inventoryService.details(detailsDTO);
    }

    @ApiOperation("商品选择-确认")
    @PostMapping("/confirm")
    public ApiRes<ApplyIdVO> confirm(@RequestBody ConfirmListDTO ConfirmListDTO) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return inventoryService.confirm(ConfirmListDTO, jeeUserDetails);
    }

    @ApiOperation("商品选择确认-回显")
    @GetMapping("/confirmShow")
    public ApiRes<ConfirmShowVO> confirmShow(@ApiParam(name = "prodApplyId", value = "订单申请id") @RequestParam(value = "prodApplyId") @NonNull Integer prodApplyId) {
        return inventoryService.confirmShow(prodApplyId);
    }

    @ApiOperation("确认订单页-修改数量")
    @PostMapping("/updateApply")
    public ApiRes updateApply(@RequestBody UpdateApplyDTO applyDTO) {
        return inventoryService.updateApply(applyDTO);
    }

    @ApiOperation("确认订单页-添加商品")
    @PostMapping("/addProd")
    public ApiRes addProd(@RequestBody AddProdDTO applyDTO) {
        return inventoryService.addProd(applyDTO);
    }

    @ApiOperation("确认订单页-选择商品下拉框")
    @PostMapping("/chooseProd")
    public ApiRes<ChooseProdVO> chooseProd(@RequestBody ChooseProdDTO chooseProdDTO) {
        return inventoryService.chooseProd(chooseProdDTO);
    }

}