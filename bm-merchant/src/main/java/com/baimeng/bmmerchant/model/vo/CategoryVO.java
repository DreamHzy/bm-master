package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryVO implements Serializable {

    @ApiModelProperty("类目id")
    private Integer categoryId;

    @ApiModelProperty("类目名称")
    private String categoryName;

    @ApiModelProperty("检查评分")
    private String evaluateScore;

    @ApiModelProperty("评价表id")
    private String evaluateId;

    @ApiModelProperty("image")
    private String image;

}
