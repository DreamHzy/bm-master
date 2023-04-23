package com.baimeng.bmmerchant.ctrl;

import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.model.dto.*;
import com.baimeng.bmmerchant.model.vo.CategoryVO;
import com.baimeng.bmmerchant.model.vo.ContenctVO;
import com.baimeng.bmmerchant.service.InspectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "收市检查相关接口")
@RestController
@RequestMapping("/inspect")
@Slf4j
public class InspectController extends CommonCtrl {

    @Resource
    InspectService inspectService;

    @ApiOperation("查询类目")
    @PostMapping("/queryCategory")
    public ApiRes<CategoryVO> queryCategory(@RequestBody CategoryDTO categoryDTO) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return inspectService.queryCategoryService(categoryDTO, jeeUserDetails);
    }

//    @ApiOperation("保存检查评价")
//    @PostMapping("/saveAssess")
//    public ApiRes saveAssess(@RequestBody InspectListDTO inspectDTOList) {
//        ///当前用户信息
//        JeeUserDetails jeeUserDetails = getCurrentUser();
//        return inspectService.saveAssessService(inspectDTOList, jeeUserDetails);
//    }
//
//    @ApiOperation("编辑检查评价")
//    @PostMapping("/updateAssess")
//    public ApiRes updateAssess(@RequestBody InspectsDTO inspectsDTO) {
//        ///当前用户信息
//        JeeUserDetails jeeUserDetails = getCurrentUser();
//        return inspectService.updateAssess(inspectsDTO, jeeUserDetails);
//    }

    /**
     * 编辑和新增评价
     * @param
     * @return
     */
    @ApiOperation("编辑和新增评价")
    @PostMapping("/updateOrAddAssess")
    public ApiRes updateOrAddAssess(@RequestBody UpdateOrAddAssessDTO updateOrAddAssessDTO) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return inspectService.updateOrAddAssess(updateOrAddAssessDTO, jeeUserDetails);
    }


    @ApiOperation("查看评价")
    @PostMapping("/queryAssess")
    public ApiRes<ContenctVO> queryAssess(@RequestBody AsseaaDTO asseaaDTO) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();

        return inspectService.queryAssessService(asseaaDTO, jeeUserDetails);
    }

    @ApiOperation("管理员-查看类目")
    @PostMapping("/adminQueryCategory")
    public ApiRes<CategoryVO> adminQueryCategory(@RequestBody AdminCategoryDTO categoryDTO) {
        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        return inspectService.adminQueryCategory(categoryDTO, jeeUserDetails);
    }
}