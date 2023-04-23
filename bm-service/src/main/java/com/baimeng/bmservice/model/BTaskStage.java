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
 * 门店任务阶段表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BTaskStage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "stage_id", type = IdType.AUTO)
    private Integer stageId;

    /**
     * 门店编号
     */
    private String storeNo;

    /**
     * 阶段名称
     */
    private String name;

    /**
     * 开始时间 eg:11:00
     */
    private Date startTime;

    /**
     * 结束时间  eg:12:00
     */
    private Date endTime;

    /**
     * 0-停用, 1-正常
     */
    private Byte state;

    private Date createdAt;

    private Date updatedAt;


    private String iconUrl;

    /**
     * 图片展示
     */
    private String imageUrl;

    //gw
    public static final LambdaQueryWrapper<BTaskStage> gw(){
        return new LambdaQueryWrapper<>();
    }
}
