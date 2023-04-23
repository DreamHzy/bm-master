package com.baimeng.bmmerchant.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProdApplyDTO implements Serializable {

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
