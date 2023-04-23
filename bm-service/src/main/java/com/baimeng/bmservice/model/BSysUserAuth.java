package com.baimeng.bmservice.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.management.Query;

/**
 * <p>
 * 系统用户认证表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BSysUserAuth implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "auth_id", type = IdType.AUTO)
    private Integer authId;

    /**
     * user_id
     */
    private Integer sysUserId;

    /**
     * 登录类型  1-登录账号
     */
    private Byte identityType;

    /**
     * 认证标识 ( 用户名 | open_id )
     */
    private String identifier;

    /**
     * 密码凭证
     */
    private String credential;

    /**
     * salt
     */
    private String salt;

    /**
     * 所属系统： MGR-运营平台, MCH-商户中心
     */
    private String sysType;

    /**
     * 状态 0-停用, 1-启用
     */
    private Byte state;

    private Date createdAt;

    private Date updatedAt;



    //gw
    public static final LambdaQueryWrapper<BSysUserAuth> gw(){
        return new LambdaQueryWrapper<>();
    }

}
