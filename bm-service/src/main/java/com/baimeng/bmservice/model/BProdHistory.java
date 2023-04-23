package com.baimeng.bmservice.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品库存历史
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BProdHistory implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "prod_history_id", type = IdType.AUTO)
    private Integer prodHistoryId;

    /**
     * stock_product_inventory表id
     */
    private Integer prodId;

    /**
     * type 2时该字段生效
     */
    private Integer inventoryDetailsId;

    /**
     * 入库数
     */
    private Integer warehousing;

    /**
     * 领用数
     */
    private Integer receiving;

    /**
     * 操作数量
     */
    private Integer quantity;


    private Integer stock;

    /**
     * 1入库  2领用
     */
    private Integer type;

    /**
     * 操作人
     */
    private Integer sysUserId;

    private Date createdAt;

    private Date updatedAt;




}
