package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class NumberDTO implements Serializable {

    @ApiModelProperty("日期")
    private String takeoutDate;

    @ApiModelProperty("外卖平台(0饿了么 1美团)")
    private Byte takeoutPlatform;

}
