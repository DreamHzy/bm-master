package com.baimeng.bmmerchant.service;

import cn.hutool.core.util.IdUtil;
import com.baimeng.bmcore.cache.ITokenService;
import com.baimeng.bmcore.constants.CS;
import com.baimeng.bmcore.exception.BizException;
import com.baimeng.bmcore.exception.JeepayAuthenticationException;
import com.baimeng.bmcore.jwt.JWTPayload;
import com.baimeng.bmcore.jwt.JWTUtils;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.config.SystemYmlConfig;
import com.baimeng.bmmerchant.mapper.MtSysEntitlementMapper;
import com.baimeng.bmservice.impl.*;
import com.baimeng.bmservice.mapper.BSysEntitlementMapper;
import com.baimeng.bmservice.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 认证Service
 *
 * @modify zhuxiao
 * @site https://www.jeequan.com
 * @date 2021-04-27 15:50
 */
@Slf4j
@Service
public class AuthService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private BSysEntitlementMapper sysEntitlementMapper;
    @Resource
    private MtSysEntitlementMapper mtSysEntitlementMapper;
    @Resource
    private BMchInfoService mchInfoService;
    @Resource
    private SystemYmlConfig systemYmlConfig;
    @Resource
    private IBSysRoleService ibSysRoleService;
    @Resource
    private IBSysRoleEntRelaService ibSysRoleEntRelaService;
    @Resource
    private IBSysUserRoleRelaService ibSysUserRoleRelaService;
    @Resource
    private IBSysEntitlementService ibSysEntitlementService;


    public String auth(String username, String password) {


        //1. 生成spring-security usernamePassword类型对象
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        //spring-security 自动认证过程；
        // 1. 进入 JeeUserDetailsServiceImpl.loadUserByUsername 获取用户基本信息；
        //2. SS根据UserDetails接口验证是否用户可用；
        //3. 最后返回loadUserByUsername 封装的对象信息；
        Authentication authentication = null;

        try {
            authentication = authenticationManager.authenticate(upToken);
        } catch (JeepayAuthenticationException jex) {
            throw jex.getBizException() == null ? new BizException(jex.getMessage()) : jex.getBizException();
        } catch (BadCredentialsException e) {
            throw new BizException("用户名/密码错误！");
        } catch (AuthenticationException e) {
            log.error("AuthenticationException:", e);
            throw new BizException("认证服务出现异常， 请重试或联系系统管理员！");
        }
        JeeUserDetails jeeUserDetails = (JeeUserDetails) authentication.getPrincipal();
        //验证通过后 再查询用户角色和权限信息集合
        BSysUser sysUser = jeeUserDetails.getSysUser();
        //无菜单时进行提示
        if (mtSysEntitlementMapper.userHasLeftMenu(sysUser.getSysUserId(), CS.SYS_TYPE.MDC_APP) <= 0) {
            throw new BizException("当前用户未分配任何菜单权限，请联系管理员进行分配后再登录！");
        }
        // 查询当前用户的商户信息
        BMchInfo mchInfo = mchInfoService.getById(sysUser.getBelongInfoId());
        if (mchInfo != null) {
            // 判断当前商户状态是否可用
            if (mchInfo.getState() == CS.NO) {
                throw new BizException("当前商户状态不可用！");
            }
        }
        // 放置权限集合
        jeeUserDetails.setAuthorities(getUserAuthority(sysUser));
        //生成token
        String cacheKey = CS.getCacheKeyToken(sysUser.getSysUserId(), IdUtil.fastUUID());

        //生成iToken 并放置到缓存
        ITokenService.processTokenCache(jeeUserDetails, cacheKey); //处理token 缓存信息

        //将信息放置到Spring-security context中
        UsernamePasswordAuthenticationToken authenticationRest = new UsernamePasswordAuthenticationToken(jeeUserDetails, null, jeeUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationRest);
        //返回JWTToken
        return JWTUtils.generateToken(new JWTPayload(jeeUserDetails), systemYmlConfig.getJwtSecret());
    }

    public List<SimpleGrantedAuthority> getUserAuthority(BSysUser sysUser) {
        //用户拥有的角色集合  需要以ROLE_ 开头,  用户拥有的权限集合
        List<String> roleList = findListByUser(sysUser.getSysUserId());
        List<String> entList = selectEntIdsByUserId(sysUser.getSysUserId(), CS.SYS_TYPE.MDC_APP);

        List<SimpleGrantedAuthority> grantedAuthorities = new LinkedList<>();
        roleList.stream().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role)));
        entList.stream().forEach(ent -> grantedAuthorities.add(new SimpleGrantedAuthority(ent)));
        return grantedAuthorities;
    }


    /**
     * 根据用户查询全部角色集合
     **/
    public List<String> findListByUser(Integer sysUserId) {
        List<String> result = new ArrayList<>();
        ibSysUserRoleRelaService.list(BSysUserRoleRela.gw().eq(BSysUserRoleRela::getSysUserId, sysUserId)).stream().forEach(r -> result.add(r.getRoleId() + ""));
        return result;
    }


    /**
     * 根据人查询出所有权限ID集合
     */
    public List<String> selectEntIdsByUserId(Integer userId, String sysType) {
        List<String> result = mtSysEntitlementMapper.selectEntIdsByUserId(userId, sysType);
        return result;
    }

}
