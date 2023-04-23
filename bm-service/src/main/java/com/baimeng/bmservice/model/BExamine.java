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
 * 
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BExamine implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "examine_id", type = IdType.AUTO)
    private Integer examineId;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 0审核中 1审核通过 2撤回 3驳回 4 已完成
     */
    private Byte status;

    /**
     * 最新审核时间
     */
    private Date examineTime;

    /**
     * 外部关联id
     */
    private Integer otherId;

    /**
     * 0出入库 此时other_id关联prod_apply表id
     */
    private Integer type;

    /**
     * 显示字段1
     */
    private String textMoney;


    private String storeNo;

    private Integer applyUserId;


    //gw
    public static final LambdaQueryWrapper<BExamine> gw(){
        return new LambdaQueryWrapper<>();
    }
}
