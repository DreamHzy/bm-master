package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class InspectDTO implements Serializable {

    @ApiModelProperty(value = "类目id",example = "1")
    private Integer categoryId;

    @ApiModelProperty("内容id")
    private String contenctId;

    @ApiModelProperty("评价人")
    private Integer userId;

    @ApiModelProperty("检查评分")
    private String evaluateScore;

    @ApiModelProperty("检查评价")
    private String evaluateAssess;

    @ApiModelProperty(value = "文件类型 1、excel表格 2、word 3、视频 4、照片",example = "1")
    private Integer type;

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("文件地址")
    private List<FileUrlDTO> list;

//    @ApiModelProperty("检查图片地址")
//    private Integer fileUrl;

}
