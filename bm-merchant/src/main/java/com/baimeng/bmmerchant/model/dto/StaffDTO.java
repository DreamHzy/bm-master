package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class StaffDTO {

    private String attendanceConfigId;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("打卡图片")
    private String image;
}
