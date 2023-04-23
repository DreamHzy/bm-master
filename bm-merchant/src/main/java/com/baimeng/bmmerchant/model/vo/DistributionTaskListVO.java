package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class DistributionTaskListVO {
    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty("编号")
    private String userNo;

    @ApiModelProperty("阶段信息")
    private List<DistributionStageVO> distributionStageVOS;

}
