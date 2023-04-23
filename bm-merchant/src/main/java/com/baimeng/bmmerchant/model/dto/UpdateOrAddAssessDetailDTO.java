package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UpdateOrAddAssessDetailDTO {

    @ApiModelProperty("内容id")
    private String contenctId;

    @ApiModelProperty("检查评分")
    private String evaluateScore;

    @ApiModelProperty("检查评价")
    private String evaluateAssess;

    @ApiModelProperty("文件地址")
    private List<String> list;
}
