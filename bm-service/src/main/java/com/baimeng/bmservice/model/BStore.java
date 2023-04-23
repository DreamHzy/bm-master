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
 * 门店信息表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BStore implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 门店编号
     */
    private String storeNo;

    /**
     * 所属商户
     */
    private String mchNo;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 街道
     */
    private String street;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 0-停用, 1-正常
     */
    private Byte state;

    private Date createdAt;

    private Date updatedAt;

    //gw
    public static final LambdaQueryWrapper<BStore> gw(){
        return new LambdaQueryWrapper<>();
    }
}
