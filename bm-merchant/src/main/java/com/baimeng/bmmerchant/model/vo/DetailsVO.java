package com.baimeng.bmmerchant.model.vo;

import com.baimeng.bmmerchant.model.dto.InventoryDetailsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DetailsVO implements Serializable {

    @ApiModelProperty("商品详情")
    AdminInventoryVO adminInventoryVO;
    @ApiModelProperty("商品列表")
    List<InventoryDetailsDTO> details;
}
