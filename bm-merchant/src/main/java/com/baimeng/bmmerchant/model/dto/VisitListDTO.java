package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class VisitListDTO implements Serializable {
//
//    @ApiModelProperty("门店编号")
//    private String storeNo;

    @ApiModelProperty("外卖日期")
    private String takeoutDate;

}
