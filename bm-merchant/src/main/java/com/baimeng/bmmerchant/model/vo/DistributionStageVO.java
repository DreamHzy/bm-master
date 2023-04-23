package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class DistributionStageVO {

    @ApiModelProperty("阶段id")
    private String stageId;

    @ApiModelProperty("阶段名称")
    private String name;

    @ApiModelProperty("阶段开始时间")
    private String startTime;

    @ApiModelProperty("阶段结束时间")
    private String endTime;

    private String url;

    @ApiModelProperty("任务列表")
    private List<DistributionTaskListDetailVO> distributionTaskListDetailVOS;
}
