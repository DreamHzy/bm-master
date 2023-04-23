package com.baimeng.bmmerchant.model.vo;

import com.baimeng.bmmerchant.model.dto.FileUrlDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContenctDetailVO {

    private String contenctId;

    @ApiModelProperty("检查内容")
    private String contenct;

    @ApiModelProperty("检查评分")
    private String evaluateScore;

    @ApiModelProperty("检查评价")
    private String evaluateAssess;

    @ApiModelProperty("图片地址")
    List<String> list = new ArrayList<>();


}
