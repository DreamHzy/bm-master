package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AsseaaDTO implements Serializable {

    @ApiModelProperty("日期")
    private String day;

    @ApiModelProperty("类目id")
    private String categoryId;

}
