package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class EvaluateDTO {

    @ApiModelProperty("日期")
    private String day;
    @ApiModelProperty("评价")
    private String msg;
    @ApiModelProperty("星级")
    private String score;
    @ApiModelProperty("图片集合")
    private List<String> imagList;
    @ApiModelProperty("员工id")
    private String userId;
}
