package com.baimeng.bmmerchant.ctrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baimeng.bmcomponentsoss.config.AliyunOssYmlConfig;
import com.baimeng.bmcomponentsoss.model.OssFileConfig;
import com.baimeng.bmcore.constants.CS;
import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmcore.utils.TreeDataBuilder;
import com.baimeng.bmservice.impl.BSysEntitlementService;
import com.baimeng.bmservice.model.BSysEntitlement;
import com.baimeng.bmservice.model.BSysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 当前登录者的信息相关接口
 *
 * @author terrfly
 * @modify zhuxiao
 * @site https://www.jeequan.com
 * @date 2021-04-27 15:50
 */
@Api(tags = "用户信息相关接口")
@RestController
@RequestMapping("api/current")
public class CurrentUserController extends CommonCtrl {

    @Autowired
    private BSysEntitlementService sysEntitlementService;
    @Resource
    AliyunOssYmlConfig aliyunOssYmlConfig;


    @ApiOperation("获取用户信息")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ApiRes currentUserInfo() {

        ///当前用户信息
        JeeUserDetails jeeUserDetails = getCurrentUser();
        BSysUser user = jeeUserDetails.getSysUser();

        //1. 当前用户所有权限ID集合
        List<String> entIdList = new ArrayList<>();
        jeeUserDetails.getAuthorities().stream().forEach(r -> entIdList.add(r.getAuthority()));
        List<BSysEntitlement> allMenuList = new ArrayList<>();    //所有菜单集合
        //2. 查询出用户所有菜单集合 (包含左侧显示菜单 和 其他类型菜单 )
        if (!entIdList.isEmpty()) {
            allMenuList = sysEntitlementService.
                    list(BSysEntitlement.gw().in(BSysEntitlement::getEntId, entIdList).
                            in(BSysEntitlement::getEntType, Arrays.asList(CS.ENT_TYPE.MENU_LEFT, CS.ENT_TYPE.MENU_OTHER)).
                            eq(BSysEntitlement::getSysType, CS.SYS_TYPE.MDC_APP).eq(BSysEntitlement::getState, CS.PUB_USABLE));
            allMenuList.stream().forEach(
                    bSysEntitlement -> {
                        bSysEntitlement.setMenuIcon(aliyunOssYmlConfig.getUrl() + bSysEntitlement.getMenuIcon());
                    }
            );
        }

        //4. 转换为json树状结构
        JSONArray jsonArray = (JSONArray) JSON.toJSON(allMenuList);
        List<JSONObject> allMenuRouteTree = new TreeDataBuilder(jsonArray, "entId", "pid", "children", "entSort", true).buildTreeObject();

        //1. 所有权限ID集合
        user.addExt("entIdList", entIdList);
        user.addExt("allMenuRouteTree", allMenuRouteTree);
        return ApiRes.ok(getCurrentUser().getSysUser());
    }


}
