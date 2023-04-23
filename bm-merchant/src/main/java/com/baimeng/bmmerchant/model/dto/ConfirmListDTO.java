package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ConfirmListDTO implements Serializable {

    @ApiModelProperty("出入库类型(1-出库 2-入库)")
    private String applyType;

    @ApiModelProperty("商品列表")
    List<ConfirmDTO> confirmDTOList;

}
