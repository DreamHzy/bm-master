package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class InventoryDetailsDTO implements Serializable {

    @ApiModelProperty("时间")
    private String createdTime;
    @ApiModelProperty("类型")
    private String type;
    @ApiModelProperty("库存")
    private String inventory;
    @ApiModelProperty("操作人")
    private String sysUserId;
    @ApiModelProperty("操作数量")
    private String quantity;
    @ApiModelProperty("操作金额")
    private String price;
}
