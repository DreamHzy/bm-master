package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdatetaskDTO {

    @ApiModelProperty("任务id")
    private String taskUserClockTaskId;

    @ApiModelProperty("逾期原因")
    private String msg;
}
