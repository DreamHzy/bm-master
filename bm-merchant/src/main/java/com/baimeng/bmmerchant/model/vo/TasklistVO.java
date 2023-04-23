package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TasklistVO {
    @ApiModelProperty("id")
    private String clockId;

    @ApiModelProperty("阶段名称")
    private String stageName;

    @ApiModelProperty("任务图片")
    private String imageUrl;

    @ApiModelProperty("任务图片")
    private String iconUrl;

    @ApiModelProperty("开始时间")
    private String endTime;

    @ApiModelProperty("结束时间")
    private String startTime;

    @ApiModelProperty("状态 0待打卡 1待完成 2已完成 3已完成(逾期) ")
    private String status;
    @ApiModelProperty("逾期描述 ")
    private String statusMsg;
    @ApiModelProperty("任务总数")
    private String total;

    @ApiModelProperty("完成任务数")
    private String finshTotal;

}
