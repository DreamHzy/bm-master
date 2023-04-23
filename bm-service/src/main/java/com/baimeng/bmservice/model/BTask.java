package com.baimeng.bmservice.model;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 门店任务表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BTask implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 任务编号
     */
    private String taskNo;

    /**
     * 任务场景id
     */
    private Integer sceneId;


    private Integer stageId;

    /**
     * 门店编号
     */
    private String storeNo;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务描述
     */
    private String describe;

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
     * 图片
     */
    private String imageUrl;


    //gw
    public static final LambdaQueryWrapper<BTask> gw() {
        return new LambdaQueryWrapper<>();
    }
}
