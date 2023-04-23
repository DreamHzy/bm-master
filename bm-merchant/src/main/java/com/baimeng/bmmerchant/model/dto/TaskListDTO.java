package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TaskListDTO implements Serializable {

    @ApiModelProperty("用户id")
    private String sysUserId;

    @ApiModelProperty("编号")
    private String number;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("待完成任务")
    private String waitComplete;

    @ApiModelProperty("已完成任务")
    private String alreadyComplete;

    @ApiModelProperty("逾期完成")
    private String overdueComplete;

    @ApiModelProperty("图片地址")
    private String url;

}
