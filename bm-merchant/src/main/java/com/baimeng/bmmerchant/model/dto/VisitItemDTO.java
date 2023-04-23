package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class VisitItemDTO implements Serializable {

    @ApiModelProperty(value = "外卖详情id",example = "1")
    private Integer newtakeoutId;
}
