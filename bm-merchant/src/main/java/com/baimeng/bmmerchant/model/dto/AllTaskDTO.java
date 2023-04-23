package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AllTaskDTO implements Serializable {

    @ApiModelProperty("在岗人数")
    private String onDutyAmount;

    @ApiModelProperty("休息人数")
    private String restAmount;

    @ApiModelProperty("待完成任务")
    private String waitComplete;

    @ApiModelProperty("已完成任务")
    private String alreadyComplete;

    @ApiModelProperty("逾期完成")
    private String overdueComplete;

}
