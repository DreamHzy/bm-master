package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDetailListVO {
    private String taskUserClockTaskId;

    @ApiModelProperty("任务编号")
    private String taskNo;

    @ApiModelProperty("任务名称")
    private String taskName;

    @ApiModelProperty("任务描述")
    private String describe;

    @ApiModelProperty("0待完成 1已完成 2已完成(逾期")
    private String status;

    @ApiModelProperty("状态描述")
    private String statusMsg;

    @ApiModelProperty("完成时间")
    private String finshTime;

    @ApiModelProperty("逾期描述")
    private String remark;

    @ApiModelProperty("点击完成是否弹逾期框 0否 1是")
    private String overdue;
    @ApiModelProperty("图片地址")
    private String imageUrl;

    @ApiModelProperty("有代表有教程 无值代表无教程")
    private String courseId;

    private Date endAt;

    private String createdAt;

}
