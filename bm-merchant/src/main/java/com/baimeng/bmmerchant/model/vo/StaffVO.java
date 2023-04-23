package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class StaffVO {


    @ApiModelProperty("员工编号")
    private String userNo;

    @ApiModelProperty("员工名称")
    private String userName;

    @ApiModelProperty("考勤列表")
    private List<StaffDetailVO> staffDetailVOS;


}
