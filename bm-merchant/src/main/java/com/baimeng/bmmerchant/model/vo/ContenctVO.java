package com.baimeng.bmmerchant.model.vo;

import com.baimeng.bmmerchant.model.dto.FileUrlDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ContenctVO implements Serializable {

    @ApiModelProperty("岗位")
    private String name;
    @ApiModelProperty("域名")
    private String image;
    private List<ContenctDetailVO> contenctDetailVOList;
}
