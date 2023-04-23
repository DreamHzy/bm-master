package com.baimeng.bmservice.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 操作员<->角色 关联表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BSysUserRoleRela implements Serializable {

    private static final long serialVersionUID=1L;
    //gw
    public static final LambdaQueryWrapper<BSysUserRoleRela> gw(){
        return new LambdaQueryWrapper<>();
    }
    /**
     * 用户ID
     */
    /**
     * user_id
     */
    private Integer sysUserId;

    /**
     * 角色ID
     */
    private Integer roleId;


    /**
     * 状态 0-停用, 1-启用
     */
    private Byte state;



}
