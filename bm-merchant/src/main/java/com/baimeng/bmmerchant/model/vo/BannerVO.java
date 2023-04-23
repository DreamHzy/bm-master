package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BannerVO {

    @ApiModelProperty("banner图片地址")
    private String imageUrl;
}
