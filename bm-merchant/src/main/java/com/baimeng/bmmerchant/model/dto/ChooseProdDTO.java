package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class ChooseProdDTO implements Serializable {

    @ApiModelProperty("申请id")
    private Integer applyId;

    @ApiModelProperty("商品名称")
    private String prodName;

}
