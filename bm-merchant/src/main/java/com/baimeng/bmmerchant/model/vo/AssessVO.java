package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AssessVO implements Serializable {

    @ApiModelProperty("评价内容")
    List<ContenctVO> contenctVO;
    @ApiModelProperty("评价图片地址")
    List<String> list;

}
