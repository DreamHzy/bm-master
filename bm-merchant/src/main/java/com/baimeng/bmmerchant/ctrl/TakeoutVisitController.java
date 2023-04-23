package com.baimeng.bmmerchant.ctrl;

import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.comment.PageWrap;
import com.baimeng.bmmerchant.model.dto.*;
import com.baimeng.bmmerchant.model.vo.TakeoutItemVO;
import com.baimeng.bmmerchant.model.vo.VisitDetailsVO;
import com.baimeng.bmmerchant.model.vo.VisitListVO;
import com.baimeng.bmmerchant.service.TakeoutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(tags = "外卖回访相关接口")
@RestController
@RequestMapping("/takeoutVisit")
@Slf4j
public class TakeoutVisitController extends CommonCtrl {

    @Resource
    TakeoutService takeoutService;

    @ApiOperation("保存回访")
    @PostMapping("/countTakeout")
    public ApiRes countTakeout(@RequestBody TakeoutDTO takeoutDTO) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return takeoutService.countTakeout(takeoutDTO,jeeUserDetails);
    }

    @ApiOperation("外卖回访统计")
    @PostMapping("/takeoutVisitList")
    public ApiRes<VisitListVO> takeoutVisitList(@RequestBody VisitListDTO visitListDTO) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return takeoutService.visitListSerive(visitListDTO,jeeUserDetails);
    }

    @ApiOperation("外卖回访列表")
    @PostMapping("/takeoutVisitDetails")
    public ApiRes<VisitDetailsVO> takeoutVisitDetails(@RequestBody @Valid PageWrap<VisitDetailsDTO> pageWrap ) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return takeoutService.takeoutVisitDetailsService(pageWrap,jeeUserDetails);
    }

    @ApiOperation("外卖回访详情")
    @PostMapping("/takeoutVisitItem")
    public ApiRes<TakeoutItemVO> takeoutVisitItem(@RequestBody VisitItemDTO visitItemDTO) {
        return takeoutService.takeoutVisitItemService(visitItemDTO);
    }

    @ApiOperation("编号")
    @PostMapping("/number")
    public ApiRes<Integer> number(@RequestBody NumberDTO numberDTO) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return takeoutService.number(jeeUserDetails,numberDTO);
    }

    @ApiOperation("管理员-外卖回访统计")
    @PostMapping("/adminTakeoutVisitList")
    public ApiRes<VisitListVO> adminTakeoutVisitList(@RequestBody AdminDTO adminDTO) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return takeoutService.adminTakeout(jeeUserDetails,adminDTO);
    }

    @ApiOperation("管理员-外卖回访列表")
    @PostMapping("/adminTakeoutVisitDetails")
    public ApiRes<VisitListVO> adminTakeoutVisitDetails(@RequestBody @Valid PageWrap<AdminDetailsDTO> pageWrap) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return takeoutService.admintakeoutDetails(jeeUserDetails,pageWrap);
    }
}