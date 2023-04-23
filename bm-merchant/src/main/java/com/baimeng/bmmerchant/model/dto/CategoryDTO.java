package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {

    @ApiModelProperty("检查时间")
    private String createDay;


}
