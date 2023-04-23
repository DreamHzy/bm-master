package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApplyIdVO implements Serializable {
    @ApiModelProperty("订单申请id")
    private Integer prodApplyId;
}
