package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TaskManagementDTO implements Serializable {

    @ApiModelProperty("日期")
    private String date;

}
