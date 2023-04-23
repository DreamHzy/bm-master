package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ConfirmListTwoDTO implements Serializable {

    @ApiModelProperty("商品id")
    private String prodId;

    @ApiModelProperty("申请订单id")
    private String prodApplyId;

    @ApiModelProperty("订单申请详id")
    private String prodApplyDetailsId;

    @ApiModelProperty("商品名称")
    private String prodName;

    @ApiModelProperty("商品单位")
    private String unit;

    @ApiModelProperty(value = "商品数量",example = "1")
    private Integer prodAmount;

    @ApiModelProperty("图片地址")
    private String image;

    @ApiModelProperty("商品库存")
    private String inventory;

    @ApiModelProperty("商品单价")
    private BigDecimal price;

}
