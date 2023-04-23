package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TaskDTO implements Serializable {

    @ApiModelProperty("门店id(不传查全部)")
    private String storeNo;

    @ApiModelProperty("日期")
    private String date;

}
