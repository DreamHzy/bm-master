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
 * 商品库存表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BProd implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "prod_id", type = IdType.AUTO)
    private Integer prodId;

    /**
     * 商品类别id
     */
    private Integer prodCategoryId;

    /**
     * 门店编号
     */
    private String storeNo;

    /**
     * 商品名称
     */
    private String prodName;

    /**
     * 入库数量
     */
    private Integer warehousingCount;

    /**
     * 领用数量
     */
    private Integer collectCount;

    /**
     * 图片地址
     */
    private String image;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 单位
     */
    private String unit;

    /**
     * 0-停用, 1-正常
     */
    private Byte state;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

    /**
     * 版本号
     */
    private Integer version;


}
