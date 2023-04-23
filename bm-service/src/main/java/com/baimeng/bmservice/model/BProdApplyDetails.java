package com.baimeng.bmservice.model;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品出入库申请详情表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BProdApplyDetails implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 订单申请详情表id
     */
    @TableId(value = "prod_apply_details_id", type = IdType.AUTO)
    private Integer prodApplyDetailsId;

    /**
     * 订单申请表id
     */
    private Integer prodApplyId;

    /**
     * 商品表id
     */
    private Integer prodId;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 商品数量
     */
    private Integer amount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


    //gw
    public static final LambdaQueryWrapper<BProdApplyDetails> gw() {
        return new LambdaQueryWrapper<>();
    }

}
