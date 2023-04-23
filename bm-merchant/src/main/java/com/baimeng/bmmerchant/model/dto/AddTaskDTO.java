package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class AddTaskDTO {
    @ApiModelProperty("任务编号")
    private List<String> taskNo;
    @ApiModelProperty("员工id")
    private String userId;
    @ApiModelProperty("阶段id")
    private String stageId;
}
