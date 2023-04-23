package com.baimeng.bmservice.model;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 任务-阶段-用户关联表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BTaskStageUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "task_stage_user_id", type = IdType.AUTO)
    private Integer taskStageUserId;

    /**
     * 任务编号
     */
    private String taskNo;

    /**
     * 阶段id
     */
    private Integer stageId;

    /**
     * 用户id
     */
    private Integer sysUserId;

    /**
     * 0-停用, 1-正常 2待生效 3待失效
     */
    private Byte state;

    /**
     * 生效时间
     */
    private Date effectAt;

    /**
     * 失效时间
     */
    private Date invalidAt;

    private Date createdAt;

    private Date updatedAt;

    private String storeNo;

    //gw
    public static final LambdaQueryWrapper<BTaskStageUser> gw() {
        return new LambdaQueryWrapper<>();
    }
}
