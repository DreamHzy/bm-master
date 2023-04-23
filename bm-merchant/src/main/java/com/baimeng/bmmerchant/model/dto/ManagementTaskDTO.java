package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ManagementTaskDTO {
    @ApiModelProperty("日期")
    private String date;

    @ApiModelProperty("门店编号")
    private String storeNo;

}
