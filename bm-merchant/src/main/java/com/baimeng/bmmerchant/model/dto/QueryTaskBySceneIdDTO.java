package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryTaskBySceneIdDTO {
    @ApiModelProperty("员工id")
    private String userId;
    @ApiModelProperty("场景id")
    private String sceneId;
    @ApiModelProperty("阶段id")
    private String stageId;
}
