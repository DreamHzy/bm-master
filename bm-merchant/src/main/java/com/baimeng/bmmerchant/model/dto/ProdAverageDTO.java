package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProdAverageDTO implements Serializable {

    @ApiModelProperty("商品id")
    private String prodId;

    @ApiModelProperty("商品日均")
    private String dayAverage;

}
