package com.baimeng.bmservice.model;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 新增外卖回访表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BNewTakeout implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 新增外卖回访表主键
     */
    @TableId(value = "new_takeout_id", type = IdType.AUTO)
    private Integer newTakeoutId;

    /**
     * 门店编号
     */
    private String storeNo;

    /**
     * 用户id
     */
    private String sysUserId;

    /**
     * 外卖日期
     */
    private DateTime takeoutDate;

    /**
     * 编号
     */
    private Integer number;

    /**
     * 外卖平台(0饿了么 1美团)
     */
    private Byte takeoutPlatform;

    /**
     * 口味多选(0太淡 1太辣 2太咸)
     */
    private String tasteMultiple;

    /**
     * 分量多选(0鱼太少 1菜太少)
     */
    private String weightMultiple;

    /**
     * 异物多选(0有头发 1菜太少)
     */
    private String abnormalMultiple;

    /**
     * 少漏
     */
    private String omission;

    /**
     * 电话反感(0是 1否)
     */
    private Integer phoneDisgust;

    /**
     * 好评意向(0是 1否)
     */
    private Integer goodIntentions;

    /**
     * 添加微信(0是 1否)
     */
    private Integer addWechat;

    /**
     * 外卖反馈
     */
    private String takeoutFeedback;

    /**
     * 外卖返利
     */
    private BigDecimal returnMoney;

    private Date createDay;

    private Date createAt;

    private Date updateAt;

    /**
     * (0启用 1禁用)
     */
    private Byte state;


}
