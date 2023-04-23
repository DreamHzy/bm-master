package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StaffDetailVO {

    private String attendanceConfigId;

    private String sysUserId;

    @ApiModelProperty("图片")
    private String url;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("0未打 1正常 2逾期 3早退")
    private String state;

    @ApiModelProperty("状态描述")
    private String stateMsg;

    @ApiModelProperty("上班/下班时间")
    private String workTime;

    @ApiModelProperty("打卡时间")
    private String time;
}
