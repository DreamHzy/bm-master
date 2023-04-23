package com.baimeng.bmservice.model;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 任务场景表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BTaskScene implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "scene_id", type = IdType.AUTO)
    private Integer sceneId;

    /**
     * 任务编号
     */
    private String storeNo;

    /**
     * 场景名称
     */
    private String name;

    /**
     * 0-停用, 1-正常
     */
    private Byte state;

    /**
     * 排序1 2 34
     */
    private Integer sort;

    private Date createdAt;

    private Date updatedAt;


    //gw
    public static final LambdaQueryWrapper<BTaskScene> gw() {
        return new LambdaQueryWrapper<>();
    }
}
