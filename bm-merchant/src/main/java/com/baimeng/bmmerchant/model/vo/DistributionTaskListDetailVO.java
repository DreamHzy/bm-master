package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DistributionTaskListDetailVO {

    @ApiModelProperty("taskStageUserId")
    private String taskStageUserId;

    private Integer stageId;

    @ApiModelProperty("任务编号")
    private String taskNo;
    @ApiModelProperty("任务名称")
    private String name;

    @ApiModelProperty("1-正常 2待生效 3待失效")
    private String state;

    @ApiModelProperty("状态描述")
    private String stateMsg;

    @ApiModelProperty("生效时间")
    private String effectAt;

    @ApiModelProperty("失效时间")
    private String invalidAt;


}
