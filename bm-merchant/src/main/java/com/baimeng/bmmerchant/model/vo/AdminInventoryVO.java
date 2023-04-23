package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AdminInventoryVO implements Serializable {

    @ApiModelProperty("商品id")
    private String prodId;

    @ApiModelProperty("商品名称")
    private String prodName;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("商品库存")
    private String inventory;

    @ApiModelProperty("商品单价")
    private String price;

    @ApiModelProperty("商品总价")
    private String totalAmount;

    @ApiModelProperty("商品日均")
    private String dayAverage;

    @ApiModelProperty("图片地址")
    private String image;


}
