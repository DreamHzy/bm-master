package com.baimeng.bmservice.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 区域表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BArea implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "area_id", type = IdType.AUTO)
    private Integer areaId;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 城市id
     */
    private Integer cityId;


}
