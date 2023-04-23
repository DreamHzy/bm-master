package com.baimeng.bmservice.model;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.management.Query;

/**
 * <p>
 * 门店用户关联表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BStoreSysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String storeNo;

    private Integer sysUserId;

    /**
     * 状态 0-停用 1-启用
     */
    private Byte state;

    private Date createdAt;

    private Date updatedAt;

    /**
     * 0老板 1门店员工
     */
    private Byte position;


    //gw
    public static final LambdaQueryWrapper<BStoreSysUser> gw() {
        return new LambdaQueryWrapper<>();
    }
}
