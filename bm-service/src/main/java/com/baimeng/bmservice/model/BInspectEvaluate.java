package com.baimeng.bmservice.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 收市检查内容评价表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("b_inspect_evaluate")
public class BInspectEvaluate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "evaluate_id", type = IdType.AUTO)
    private Integer evaluateId;

    /**
     * 收市检查内容表id
     */
    @TableField("contenct_id")
    private Integer contenctId;

    /**
     * 类目id
     */
    @TableField("category_id")
    private Integer categoryId;

    /**
     * 检查评分
     */
    @TableField("evaluate_score")

    private String evaluateScore;

    /**
     * 检查评价
     */
    @TableField("evaluate_assess")

    private String evaluateAssess;

    /**
     * 0停用 1启用
     */

    private Byte state;

    /**
     * 评价人
     */
    @TableField("sys_user_id")

    private Integer sysUserId;

    private Date createdDay;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    @TableField("updated_at")

    private Date updatedAt;

    //gw
    public static final LambdaQueryWrapper<BInspectEvaluate> gw() {
        return new LambdaQueryWrapper<>();
    }

}
