package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AdminCategoryDTO implements Serializable {

    @ApiModelProperty("检查时间")
    private String createDay;

    @ApiModelProperty("门店编号")
    private String storeNo;

}
