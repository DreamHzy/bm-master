package com.baimeng.bmmerchant.service.Impl;

import com.baimeng.bmcomponentsoss.config.AliyunOssYmlConfig;
import com.baimeng.bmcore.constants.CS;
import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.mapper.InspectMapper;
import com.baimeng.bmmerchant.model.dto.*;
import com.baimeng.bmmerchant.model.vo.CategoryVO;
import com.baimeng.bmmerchant.model.vo.ContenctDetailVO;
import com.baimeng.bmmerchant.model.vo.ContenctVO;
import com.baimeng.bmmerchant.service.InspectService;
import com.baimeng.bmservice.impl.IBFileService;
import com.baimeng.bmservice.impl.IBInspectCategoryService;
import com.baimeng.bmservice.impl.IBInspectEvaluateService;
import com.baimeng.bmservice.impl.IBStoreSysUserService;
import com.baimeng.bmservice.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class InspectServiceImpl implements InspectService {

    @Resource
    IBInspectEvaluateService bInspectEvaluateService;

    @Resource
    IBFileService fileService;

    @Resource
    InspectMapper inspectMapper;

    @Resource
    AliyunOssYmlConfig aliyunOssYmlConfig;

    @Resource
    IBStoreSysUserService ibStoreSysUserService;
    @Resource
    IBInspectCategoryService ibInspectCategoryService;
    @Resource
    IBInspectEvaluateService ibInspectEvaluateService;

    @Override
    public ApiRes<CategoryVO> queryCategoryService(CategoryDTO categoryDTO, JeeUserDetails jeeUserDetails) {
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);
        List<CategoryVO> categoryList = inspectMapper.selectCategory(categoryDTO.getCreateDay(), bStoreSysUser.getStoreNo(),aliyunOssYmlConfig.getUrl());
        return ApiRes.ok(categoryList);
    }

    @Override
    public ApiRes saveAssessService(InspectListDTO inspectDTOList, JeeUserDetails jeeUserDetails) {
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);
        if (inspectDTOList.getInspectDTOList().size() == 0) {
            return ApiRes.customFail("请输入检查内容");
        }
        List<BInspectEvaluate> inspectEvaluateList = new ArrayList<>();
        List<BFile> fileList = new ArrayList<>();
        for (InspectDTO inspectDTO : inspectDTOList.getInspectDTOList()) {
            BInspectEvaluate bInspectEvaluate = new BInspectEvaluate();
            bInspectEvaluate.setCategoryId(inspectDTO.getCategoryId());
            bInspectEvaluate.setEvaluateScore(inspectDTO.getEvaluateScore());
            bInspectEvaluate.setEvaluateAssess(inspectDTO.getEvaluateAssess());
            bInspectEvaluate.setContenctId(Integer.valueOf(inspectDTO.getContenctId()));
            bInspectEvaluate.setCreatedDay(new Date());
            bInspectEvaluate.setSysUserId(bStoreSysUser.getSysUserId());
            inspectEvaluateList.add(bInspectEvaluate);
            for (FileUrlDTO url : inspectDTO.getList()) {
                BFile file = new BFile();
                file.setBelongInfoId(inspectDTO.getContenctId());
                file.setType(inspectDTO.getType());
                file.setName(inspectDTO.getName());
                file.setCreatedAt(new Date());
                file.setUrl(url.getUrl());
                file.setBelongInfo("b_inspect_evaluate");
                fileList.add(file);
            }
        }
        if (!bInspectEvaluateService.saveBatch(inspectEvaluateList)) {
            return ApiRes.customFail("保存失败");
        }
        if (!fileService.saveBatch(fileList)) {
            return ApiRes.customFail("保存失败");
        }
        return ApiRes.ok("保存成功");
    }

    @Override
    public ApiRes<ContenctVO> queryAssessService(AsseaaDTO asseaaDTO, JeeUserDetails jeeUserDetails) {

        BSysUser bSysUser = jeeUserDetails.getSysUser();
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);
        ContenctVO contenctVO = new ContenctVO();
        //先查询类型信息】
        BInspectCategory bInspectCategory = ibInspectCategoryService.getById(asseaaDTO.getCategoryId());
        if (bInspectCategory == null) {
            return ApiRes.customFail("类目不存在");

        }
        contenctVO.setName(bInspectCategory.getName());
        contenctVO.setImage(aliyunOssYmlConfig.getUrl());
        //根据类目和日期查询检查相关内容
        //先查询检查内容
        List<ContenctDetailVO> contenctDetailVOList = inspectMapper.contenctDetailVOList(bInspectCategory.getCategoryId(), bStoreSysUser.getStoreNo());
        //根据检查内容查询评价信息和相关图片
        List<BInspectEvaluate> bInspectEvaluateList = inspectMapper.queryList(contenctDetailVOList, asseaaDTO.getDay());
        if (bInspectEvaluateList.size() > 0) {
            //查询对应的图片信息
            List<FileUrlDTO> url = inspectMapper.queryFile(bInspectEvaluateList, aliyunOssYmlConfig.getUrl());
            //组装数据
            contenctDetailVOList.stream().forEach(
                    contenctDetailVO -> {
                        List<String> list = new ArrayList<>();
                        //评价
                        for (BInspectEvaluate bInspectEvaluate : bInspectEvaluateList) {
                            if (contenctDetailVO.getContenctId().equals(bInspectEvaluate.getContenctId() + "")) {
                                contenctDetailVO.setEvaluateAssess(bInspectEvaluate.getEvaluateAssess());
                                contenctDetailVO.setEvaluateScore(bInspectEvaluate.getEvaluateScore());

                                //图片
                                url.stream().forEach(
                                        fileUrlDTO -> {
                                            if (fileUrlDTO.getBelongInfoId().equals(bInspectEvaluate.getEvaluateId() + "")) {
                                                list.add(fileUrlDTO.getUrl());
                                            }
                                        }
                                );
                                contenctDetailVO.setList(list);
                                break;
                            } else {
                                contenctDetailVO.setEvaluateAssess("");
                                contenctDetailVO.setEvaluateScore("");

                            }
                        }
                    }
            );
        }
        contenctVO.setContenctDetailVOList(contenctDetailVOList);
        return ApiRes.ok(contenctVO);


    }

    @Override
    public ApiRes updateOrAddAssess(UpdateOrAddAssessDTO updateOrAddAssessDTO, JeeUserDetails jeeUserDetails) {
        BSysUser sysUser = jeeUserDetails.getSysUser();
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, sysUser.getSysUserId())).get(0);
        //先查询类型信息】
        BInspectCategory bInspectCategory = ibInspectCategoryService.getById(updateOrAddAssessDTO.getCategoryId());
        if (bInspectCategory == null) {
            return ApiRes.customFail("类目不存在");
        }
        List<UpdateOrAddAssessDetailDTO> updateOrAddAssessDetailDTOS = updateOrAddAssessDTO.getUpdateOrAddAssessDetailDTOS();
        if (updateOrAddAssessDetailDTOS.size() < 0) {
            return ApiRes.customFail("请传入正确数据");
        }
        //先将原先对象修改为无效
        inspectMapper.updateByContenctIdAndCategoryIdAndDay(updateOrAddAssessDTO);
        //新增数据
        updateOrAddAssessDetailDTOS.stream().forEach(
                updateOrAddAssessDetailDTO -> {
                    BInspectEvaluate bInspectEvaluate = new BInspectEvaluate();
                    bInspectEvaluate.setEvaluateAssess(updateOrAddAssessDetailDTO.getEvaluateAssess());
                    bInspectEvaluate.setEvaluateScore(updateOrAddAssessDetailDTO.getEvaluateScore());
                    bInspectEvaluate.setCategoryId(Integer.valueOf(updateOrAddAssessDTO.getCategoryId()));
                    bInspectEvaluate.setContenctId(Integer.valueOf(updateOrAddAssessDetailDTO.getContenctId()));
                    bInspectEvaluate.setState(CS.YES);
                    bInspectEvaluate.setSysUserId(sysUser.getSysUserId());
                    bInspectEvaluate.setCreatedDay(new Date());
                    bInspectEvaluate.setCreatedAt(new Date());
                    ibInspectEvaluateService.save(bInspectEvaluate);
                    //图片信息处理
                    List<BFile> fileList = new ArrayList<>();
                    updateOrAddAssessDetailDTO.getList().stream().forEach(
                            s -> {
                                BFile file = new BFile();
                                file.setBelongInfo("b_inspect_evaluate");
                                file.setBelongInfoId(bInspectEvaluate.getEvaluateId() + "");
                                file.setUrl(s);
                                file.setType(CS.FOUR_INT);
                                file.setState(CS.YES);
                                file.setCreatedAt(new Date());
                                fileList.add(file);
                            }
                    );
                    fileService.saveBatch(fileList);
                }
        );

        return ApiRes.ok();
    }

    @Override
    public ApiRes updateAssess(InspectsDTO inspectsDTO, JeeUserDetails jeeUserDetails) {
        BSysUser bSysUser = jeeUserDetails.getSysUser();
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, bSysUser.getSysUserId())).get(0);
        BInspectEvaluate inspectEvaluate = new BInspectEvaluate();
        List<BInspectEvaluate> inspectEvaluateList = new ArrayList<>();
        List<BFile> fileList = new ArrayList<>();
        for (InspectUpdateDTO inspectUpdateDTO : inspectsDTO.getInspectDTOList()) {
            inspectEvaluate.setCategoryId(inspectUpdateDTO.getCategoryId());
            inspectEvaluate.setEvaluateId(inspectUpdateDTO.getEvaluateId());
            inspectEvaluate.setContenctId(Integer.valueOf(inspectUpdateDTO.getContenctId()));
            inspectEvaluate.setSysUserId(bStoreSysUser.getSysUserId());
            inspectEvaluate.setEvaluateScore(inspectUpdateDTO.getEvaluateScore());
            inspectEvaluate.setEvaluateAssess(inspectUpdateDTO.getEvaluateAssess());
            inspectEvaluate.setUpdatedAt(new Date());
            inspectEvaluateList.add(inspectEvaluate);
            for (FileUrlDTO url : inspectUpdateDTO.getList()) {
                BFile file = new BFile();
                file.setBelongInfoId(inspectUpdateDTO.getContenctId());
                file.setType(inspectUpdateDTO.getType());
                file.setName(inspectUpdateDTO.getName());
                file.setCreatedAt(new Date());
                file.setUrl(url.getUrl());
                file.setBelongInfo("b_inspect_evaluate");
//                file.setNoticeFileId(url.getId());
                file.setState((byte) 1);
                fileList.add(file);
            }
        }
        List<BFile> integers = inspectMapper.selectRemoveId(inspectEvaluate.getEvaluateId());
        for (BFile integer : integers) {
            integer.setState((byte) 0);
        }

        if (!fileService.updateBatchById(integers)) {
            return ApiRes.customFail("修改失败");
        }
        if (!bInspectEvaluateService.updateBatchById(inspectEvaluateList)) {
            return ApiRes.customFail("修改失败");
        }
        if (!fileService.saveBatch(fileList)) {
            return ApiRes.customFail("修改失败");
        }
        return ApiRes.ok("修改成功");
    }

    @Override
    public ApiRes<CategoryVO> adminQueryCategory(AdminCategoryDTO categoryDTO, JeeUserDetails jeeUserDetails) {
        BSysUser sysUser = jeeUserDetails.getSysUser();
        //查询用户门店信息
        BStoreSysUser bStoreSysUser = ibStoreSysUserService.list(BStoreSysUser.gw().eq(BStoreSysUser::getSysUserId, sysUser.getSysUserId())).get(0);
        List<String> list = new ArrayList<>();
        if (Objects.equals(categoryDTO.getStoreNo(), "")) {
            list = inspectMapper.selectAdminCategory(bStoreSysUser.getSysUserId());
        } else {
           list.add(categoryDTO.getStoreNo());
        }
        List<CategoryVO> categoryList = inspectMapper.selectAdmin(categoryDTO.getCreateDay(), list,aliyunOssYmlConfig.getUrl());
        return ApiRes.ok(categoryList);
    }
}