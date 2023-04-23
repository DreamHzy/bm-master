package com.baimeng.bmmerchant.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ExamineShopownerListDTO {

    @ApiModelProperty("状态 0待审核 1审核通过(目前只需要两个状态，不传的话就给空字符串) 4 已完成")
    private  String status;

}
