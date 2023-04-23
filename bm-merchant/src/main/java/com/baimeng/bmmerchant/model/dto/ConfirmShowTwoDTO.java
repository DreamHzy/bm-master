package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ConfirmShowTwoDTO implements Serializable {
    @ApiModelProperty("申请表id")
    private Integer prodApplyId;
    @ApiModelProperty("时间")
    private String createdTime;
    @ApiModelProperty("门店名称")
    private String storeName;
    @ApiModelProperty("申请人")
    private String sysUserName;
    @ApiModelProperty(value = "申请类型(1-出库 2-入库)", example = "1")
    private Integer applyType;
    @ApiModelProperty("商品数量")
    private String quantity;
    @ApiModelProperty("商品金额")
    private String price;
    @ApiModelProperty(value = "申请状态 (-1选购中 0审核中 1审核通过 2撤回 3驳回 4 已完成)", example = "1")
    private Integer status;
}
