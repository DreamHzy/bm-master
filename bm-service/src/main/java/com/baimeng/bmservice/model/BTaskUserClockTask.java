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
 * 用户打卡记录-详情
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BTaskUserClockTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "clock_task_id", type = IdType.AUTO)
    private Integer clockTaskId;

    /**
     * 打卡id
     */
    private Integer clockId;

    /**
     * 用户id
     */
    private Integer sysUserId;

    /**
     * 任务编号
     */
    private String taskNo;

    /**
     * 0待完成 1已完成 2已完成(逾期)
     */
    private Byte state;

    /**
     * 备注
     */
    private String remark;

    /**
     * 完成时间
     */
    private Date finshAt;

    private Date createdAt;

    private Date updatedAt;

    private Date endAt;


    //gw
    public static final LambdaQueryWrapper<BTaskUserClockTask> gw() {
        return new LambdaQueryWrapper<>();
    }
}
