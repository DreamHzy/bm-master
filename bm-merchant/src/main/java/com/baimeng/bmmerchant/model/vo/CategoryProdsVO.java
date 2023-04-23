package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryProdsVO implements Serializable {

    @ApiModelProperty("商品id")
    private String prodId;

    @ApiModelProperty("商品类别id")
    private String prodCategoryId;
    //    商品名称加单位
    @ApiModelProperty("商品名称")
    private String prodName;

    @ApiModelProperty("商品单位")
    private String unit;

    @ApiModelProperty("图片地址")
    private String image;

    @ApiModelProperty("商品库存")
    private String inventory;

    @ApiModelProperty("商品单价")
    private String price;
}
