package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ExamineShopownerListVO {

    @ApiModelProperty("id")
    private String examinId;

    @ApiModelProperty("申请id")
    private String prodApplyId;

    @ApiModelProperty("申请时间")
    private String applyTime;

    @ApiModelProperty("申请人")
    private String applyName;

    @ApiModelProperty("申请类型0 出库 1支出")
    private String applyType;

    @ApiModelProperty("申请类型描述")
    private String applyTypeMsg;

    @ApiModelProperty("申请状态 0审核中 1审核通过 4完成")
    private String status;

    @ApiModelProperty("状态描述")
    private String statusMag;



}
