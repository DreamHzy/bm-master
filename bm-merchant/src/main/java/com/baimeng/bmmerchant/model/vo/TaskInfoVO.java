package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TaskInfoVO {
    @ApiModelProperty("员工编号")
    private String sysNo;

    @ApiModelProperty("姓名")
    private String sysName;

    @ApiModelProperty("任务相关信息")
    private List<TasklistVO> tasklistVOList;
}
