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
 * 任务教程
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BTaskCourse implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "course_id", type = IdType.AUTO)
    private Integer courseId;

    /**
     * 任务编号
     */
    private String taskNo;

    /**
     * 消息内容
     */
    private String content;

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
    public static final LambdaQueryWrapper<BTaskCourse> gw(){
        return new LambdaQueryWrapper<>();
    }
}
