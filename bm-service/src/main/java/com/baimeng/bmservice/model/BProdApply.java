package com.baimeng.bmservice.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品出入库申请表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BProdApply implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 申请表id
     */
    @TableId(value = "prod_apply_id", type = IdType.AUTO)
    private Integer prodApplyId;

    /**
     * 门店编号
     */
    private String storeNo;

    /**
     * 申请人
     */
    private String sysUserId;

    /**
     * 申请类型(1-出库 2-入库)
     */
    private Integer applyType;

    /**
     * 商品数量
     */
    private Integer prodAmount;

    /**
     * 商品总额
     */
    private BigDecimal priceTotalAmount;

    /**
     * 0审核中 1审核通过 2撤回 3驳回 4 已完成
     */
    private Integer status;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 审核时间
     */
    private Date examineTime;

    /**
     * 审核人id管理sysuser表id
     */
    private Integer examineUserId;

    /**
     * 完成时间(领用人点击完成时间)
     */
    private Date completeTime;


}
