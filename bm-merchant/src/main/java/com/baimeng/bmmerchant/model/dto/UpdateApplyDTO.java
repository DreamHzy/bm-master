package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UpdateApplyDTO implements Serializable {

    @ApiModelProperty("订单申请详id")
    private String prodApplyDetailsId;

    @ApiModelProperty("商品数量")
    private String prodAmount;

}
