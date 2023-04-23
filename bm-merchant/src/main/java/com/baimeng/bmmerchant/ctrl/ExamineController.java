package com.baimeng.bmmerchant.ctrl;


import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.comment.PageWrap;
import com.baimeng.bmmerchant.model.dto.ExamineAdministratorsListDTO;
import com.baimeng.bmmerchant.model.dto.ExamineShopownerListDTO;
import com.baimeng.bmmerchant.model.dto.UpdateOrAddAssessDTO;
import com.baimeng.bmmerchant.model.vo.ExamineAdministratorsListVO;
import com.baimeng.bmmerchant.model.vo.ExamineAdministratorsVO;
import com.baimeng.bmmerchant.model.vo.ExamineShopownerListVO;
import com.baimeng.bmmerchant.model.vo.ExamineShopownerVO;
import com.baimeng.bmmerchant.service.ExamineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "审核相关接口")
@RestController
@RequestMapping("/index")
@Slf4j
public class ExamineController extends CommonCtrl {


    @Resource
    ExamineService examineService;


    /**
     * 出入库提交接口
     *
     * @param
     * @return
     */
    @ApiOperation("出入库提交接口")
    @GetMapping("/examineGoods")
    public ApiRes examineGoods(@ApiParam(name = "prodApplyId", value = "订单申请id") @RequestParam(value = "prodApplyId") @NonNull Integer prodApplyId) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return examineService.examineGoods(prodApplyId, jeeUserDetails);
    }

    @ApiOperation("审核列表店长")
    @PostMapping("/examineShopownerList")
    public ApiRes<ExamineShopownerVO> examineShopownerList(@RequestBody PageWrap<ExamineShopownerListDTO> pageWrap) {
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return examineService.examineShopownerList(jeeUserDetails, pageWrap);
    }

    /**
     * 审核列表管理员
     */
    @ApiOperation("审核列表管理员")
    @PostMapping("/examineAdministratorsList")
    public ApiRes<ExamineAdministratorsVO> examineAdministratorsList(
            @RequestBody PageWrap<ExamineAdministratorsListDTO> pageWrap
    ) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return examineService.examineAdministratorsList(jeeUserDetails, pageWrap);
    }

    /**
     * 管理员审核通过
     */
    @ApiOperation("管理员审核通过")
    @GetMapping("/examinePass")
    public ApiRes examinePass(
            @ApiParam(name = "prodApplyId", value = "订单申请id") @RequestParam(value = "prodApplyId") @NonNull Integer prodApplyId
    ) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return examineService.examinePass(jeeUserDetails, prodApplyId);
    }

    @ApiOperation("店长确认收货")
    @GetMapping("/shopownePass")
    public ApiRes shopownePass(
            @ApiParam(name = "prodApplyId", value = "订单申请id") @RequestParam(value = "prodApplyId") @NonNull Integer prodApplyId
    ) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return examineService.shopownePass(jeeUserDetails, prodApplyId);
    }
}
