package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ConfirmDTO implements Serializable {

    @ApiModelProperty("商品id")
    private String prodId;

//    @ApiModelProperty("商品类别id")
//    private String prodCategoryId;
//    //    商品名称加单位
//    @ApiModelProperty("商品名称")
//    private String prodName;

    @ApiModelProperty("商品数量")
    private String prodAmount;

//    @ApiModelProperty("图片地址")
//    private String image;
//
    @ApiModelProperty("商品单价")
    private String price;

}
