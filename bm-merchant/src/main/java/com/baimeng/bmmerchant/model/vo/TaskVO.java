package com.baimeng.bmmerchant.model.vo;

import com.baimeng.bmmerchant.model.dto.TaskListDTO;
import com.baimeng.bmmerchant.model.dto.TaskStateDTO;
import com.baimeng.bmmerchant.model.dto.TodayDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TaskVO implements Serializable {

    @ApiModelProperty("在岗-休息人数")
    private TodayDTO todayDTO;

    @ApiModelProperty("任务状态")
    private TaskStateDTO taskStateDTO;

    @ApiModelProperty("门店员工列表")
    private List<TaskListDTO> taskList;

}
