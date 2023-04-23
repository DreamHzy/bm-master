package com.baimeng.bmservice.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.management.Query;

/**
 * <p>
 * 系统角色权限关联表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("b_sys_role_ent_rela")
public class BSysRoleEntRela implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 权限ID
     */
    private String entId;

    /**
     * 状态 0-停用, 1-启用
     */
    private Byte state;

    //gw
    public static final LambdaQueryWrapper<BSysRoleEntRela> gw(){
        return new LambdaQueryWrapper<>();
    }

}
