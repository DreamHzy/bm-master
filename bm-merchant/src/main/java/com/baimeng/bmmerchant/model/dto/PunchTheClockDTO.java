package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class PunchTheClockDTO {

    private String clockId;

    @ApiModelProperty("图片相对路径")
    private String imageUrl;

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("地址")
    private String address;

}
