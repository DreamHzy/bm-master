package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DdetailsDTO implements Serializable {

    @ApiModelProperty("商品id")
    private String prodId;

    @ApiModelProperty(value = "开始时间",example = "yyyy-mm-dd")
    private String createTime;
}
