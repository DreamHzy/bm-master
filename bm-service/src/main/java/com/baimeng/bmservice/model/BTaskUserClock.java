package com.baimeng.bmservice.model;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户打卡记录
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BTaskUserClock implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "clock_id", type = IdType.AUTO)
    private Integer clockId;

    /**
     * 阶段id
     */
    private Integer stageId;

    /**
     * 门店编号
     */
    private String storeNo;

    /**
     * 用户id
     */
    private Integer sysUserId;

    /**
     * 0待打卡 1待完成 2已完成 3已完成(逾期)
     */
    private Byte state;


    /**
     * 打卡时间(天)
     */
    private Date clockDayAt;

    /**
     * 打卡时间
     */
    private Date clockAt;

    private Date createdAt;

    private Date updatedAt;

    private Date endAt;

    /**
     * 地址
     */
    private String address;



    //gw
    public static final LambdaQueryWrapper<BTaskUserClock> gw() {
        return new LambdaQueryWrapper<>();
    }
}
