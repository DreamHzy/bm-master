package com.baimeng.bmservice.model;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BInventoryDetails implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 订单详情表id
     */
    private Long inventoryDetailsId;

    /**
     * 订单表id
     */
    private Long inventoryOrderId;

    /**
     * 商品表id
     */
    private Long prodId;

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


}
