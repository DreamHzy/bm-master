package com.baimeng.bmservice.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 街道表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BStreet implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "street_id", type = IdType.AUTO)
    private Integer streetId;

    /**
     * 街道名称
     */
    private String name;

    /**
     * 区域id
     */
    private Integer areaId;


}
