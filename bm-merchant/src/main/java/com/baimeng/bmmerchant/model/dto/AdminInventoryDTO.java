package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AdminInventoryDTO implements Serializable {

    @ApiModelProperty("门店编号")
    private String storeNo;
}
