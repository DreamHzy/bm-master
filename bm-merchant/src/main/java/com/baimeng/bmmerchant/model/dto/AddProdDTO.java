package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AddProdDTO implements Serializable {

    @ApiModelProperty("商品id")
    private String prodId;

    @ApiModelProperty("订单申请id")
    private String prodApplyId;
//
//    @ApiModelProperty("商品数量")
    private String prodAmount;

}
