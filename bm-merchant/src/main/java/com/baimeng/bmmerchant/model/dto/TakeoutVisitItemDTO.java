package com.baimeng.bmmerchant.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class TakeoutVisitItemDTO implements Serializable {


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
    private Date takeoutDate;

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

    private String omission;

    /**
     * 异物多选(0有头发 1菜太少)
     */
    private String abnormalMultiple;

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


}
