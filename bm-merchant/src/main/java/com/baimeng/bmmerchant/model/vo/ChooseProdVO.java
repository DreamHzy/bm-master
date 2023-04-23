package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class ChooseProdVO implements Serializable {

    @ApiModelProperty("商品id")
    private String prodId;

    @ApiModelProperty("商品名称")
    private String prodName;

}
