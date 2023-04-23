package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class FileUrlDTO implements Serializable {

    @ApiModelProperty("图片id")
    private String belongInfoId;

    @ApiModelProperty("图片路径")
    private String url;


}
