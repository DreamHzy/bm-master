package com.baimeng.bmservice.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 收市检查表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("b_inspect_contenct")
public class BInspectContenct implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer contenctId;

    /**
     * 类目id
     */
    private Integer categoryId;

    /**
     * 门店编号
     */
    private String storeNo;

    /**
     * 检查内容
     */
    private String contenct;

    /**
     * 0停用 1启用
     */
    private Byte state;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
