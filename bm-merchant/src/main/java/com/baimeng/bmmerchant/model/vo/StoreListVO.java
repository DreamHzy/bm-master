package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StoreListVO {

    @ApiModelProperty("门店名称")
    private String storeName;

    @ApiModelProperty("门店编号")
    private String storeNo;

}
