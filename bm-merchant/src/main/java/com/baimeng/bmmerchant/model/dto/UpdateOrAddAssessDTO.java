package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UpdateOrAddAssessDTO {

    @ApiModelProperty("类目id")
    private String categoryId;

    @ApiModelProperty("日期")
    private String day;

    private List<UpdateOrAddAssessDetailDTO> updateOrAddAssessDetailDTOS;

}
