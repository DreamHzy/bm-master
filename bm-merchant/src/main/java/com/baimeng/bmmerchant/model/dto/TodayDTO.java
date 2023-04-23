package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TodayDTO implements Serializable {

    @ApiModelProperty("在岗人数")
    private String onDutyAmount;

    @ApiModelProperty("休息人数")
    private String restAmount;

}
