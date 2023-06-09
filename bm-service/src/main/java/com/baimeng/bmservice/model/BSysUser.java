package com.baimeng.bmservice.model;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BSysUser extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统用户ID
     */
    @TableId(value = "sys_user_id", type = IdType.AUTO)
    private Integer sysUserId;

    /**
     * 登录用户名
     */
    private String loginUsername;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 手机号
     */
    private String telphone;

    /**
     * 性别 0-未知, 1-男, 2-女
     */
    private Byte sex;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 员工编号
     */
    private String userNo;

    /**
     * 状态 0-停用 1-启用
     */
    private Byte state;

    /**
     * 所属系统： MGR-运营平台, MCH-商户平台(包含商户后台，商户小程序)
     */
    private String sysType;

    /**
     * 所属商户mch_no / 0(平台)
     */
    private String belongInfoId;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
