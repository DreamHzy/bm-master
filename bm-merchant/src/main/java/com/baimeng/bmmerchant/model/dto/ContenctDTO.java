package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ContenctDTO implements Serializable {

    @ApiModelProperty("评价id")
    private String evaluateId;

}
