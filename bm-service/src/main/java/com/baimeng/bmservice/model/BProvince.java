package com.baimeng.bmservice.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 省份表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BProvince implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "province_id", type = IdType.AUTO)
    private Integer provinceId;

    /**
     * 省份名称
     */
    private String name;


}
