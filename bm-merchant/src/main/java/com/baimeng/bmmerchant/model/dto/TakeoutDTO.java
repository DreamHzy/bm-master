package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TakeoutDTO implements Serializable {

    @ApiModelProperty("外卖日期")
    private String takeoutDate;

    @ApiModelProperty("外卖平台(0饿了么 1美团)")
    private Byte takeoutPlatform;

    @ApiModelProperty(value = "编号",example = "1")
    private Integer number;

    @ApiModelProperty("口味多选(0正常 1太淡 2太辣 3太咸)")
    private String tasteMultiple;

    @ApiModelProperty("分量多选(0正常 1鱼太少 2菜太少)")
    private String weightMultiple;

    @ApiModelProperty("异物多选(0无 1有异物 2有头发 3有蟑螂)")
    private String abnormalMultiple;

    @ApiModelProperty("少漏(0无 1少主食 2少餐具)")
    private String omission;

    @ApiModelProperty(value = "电话反感(0是 1否)",example = "1")
    private Integer phoneDisgust;

    @ApiModelProperty(value = "好评意向(0是 1否)",example = "1")
    private Integer goodIntentions;

    @ApiModelProperty(value = "添加微信(0是 1否)",example = "1")
    private Integer addWechat;

    @ApiModelProperty("外卖反馈")
    private String takeoutFeedback;

    @ApiModelProperty("用户返利(0:0元 1：3元 2：5元 3：10元)")
    private BigDecimal returnMoney;

}
