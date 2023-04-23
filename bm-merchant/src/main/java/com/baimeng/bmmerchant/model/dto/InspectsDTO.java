package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class InspectsDTO implements Serializable {

    @ApiModelProperty("修改数据")
    List<InspectUpdateDTO> inspectDTOList;

}
