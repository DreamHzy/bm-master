package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryNameDTO implements Serializable {

    @ApiModelProperty("类目主键")
    private String prodCategoryId;

    @ApiModelProperty("类目名称")
    private String name;

}
