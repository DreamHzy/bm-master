package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TaskDetailVO {

    @ApiModelProperty("clockId")
    private String clockId;

    @ApiModelProperty("员工编号")
    private String sysNo;
    @ApiModelProperty("姓名")
    private String sysName;

    @ApiModelProperty("阶段名称")
    private String stageName;

    @ApiModelProperty("任务列表")
    private List<TaskDetailListVO> taskDetailListVOS;


}
