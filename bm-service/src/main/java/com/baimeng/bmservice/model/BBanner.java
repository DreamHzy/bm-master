package com.baimeng.bmservice.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.management.Query;

/**
 * <p>
 * banner轮播图
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BBanner implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "banner_id", type = IdType.AUTO)
    private Integer bannerId;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 跳转地址
     */
    private String jumpUrl;

    /**
     * 排序1 2 3
     */
    private Integer sort;

    /**
     * 0-停用, 1-正常
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


    //gw
    public static final LambdaQueryWrapper<BBanner> gw(){
        return new LambdaQueryWrapper<>();
    }
}
