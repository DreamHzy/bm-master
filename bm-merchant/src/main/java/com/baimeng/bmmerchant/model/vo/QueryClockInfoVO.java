package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class QueryClockInfoVO {

    @ApiModelProperty("图片地址")
    private String iamgeUrl;

    @ApiModelProperty("d地址")
    private String address;

    @ApiModelProperty("时间")
    private String time;
}
