package com.baimeng.bmmerchant.model.vo;

import com.baimeng.bmmerchant.comment.PageData;
import com.baimeng.bmmerchant.model.dto.VisitDetailsListDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class VisitDetailsVO implements Serializable {

    @ApiModelProperty("返利总额")
    private String rebateSum;

    @ApiModelProperty("详情")
    private PageData<VisitDetailsListDTO> details;
}