package com.baimeng.bmservice.model;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品出入库订单
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BInventoryOrder implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 订单表id
     */
    private Integer inventoryOrderId;

    /**
     * 门店编号
     */
    private String storeNo;

    /**
     * 申请人
     */
    private String applicant;

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
     * 0审核中 1审核通过 2撤回 3驳回
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
