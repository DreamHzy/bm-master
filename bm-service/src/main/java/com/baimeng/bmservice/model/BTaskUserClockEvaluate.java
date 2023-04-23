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
 * 门店打卡评价表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BTaskUserClockEvaluate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "evaluate_file_id", type = IdType.AUTO)
    private Integer evaluateFileId;


    /**
     * 评价星级
     */
    private Integer evaluateScore;

    /**
     * 评价信息
     */
    private String evaluateMsg;

    /**
     * 评价人
     */
    private Integer sysUserId;

    /**
     * 0-停用, 1-正常
     */
    private Byte state;


    private Date createdDay;

    private Date createdAt;

    private Date updatedAt;

    private Integer clockSysUserId;


    //gw
    public static final LambdaQueryWrapper<BTaskUserClockEvaluate> gw() {
        return new LambdaQueryWrapper<>();
    }
}
