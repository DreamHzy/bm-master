package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TaskStateDTO implements Serializable {

    @ApiModelProperty("门店名称")
    private String storeName;

    @ApiModelProperty("待完成任务")
    private String waitComplete;

    @ApiModelProperty("已完成任务")
    private String alreadyComplete;

    @ApiModelProperty("逾期完成")
    private String overdueComplete;


}
