package com.baimeng.bmmerchant.model.vo;

import com.baimeng.bmmerchant.model.dto.ConfirmListTwoDTO;
import com.baimeng.bmmerchant.model.dto.ConfirmShowTwoDTO;
import com.baimeng.bmservice.model.BProdApply;
import com.baimeng.bmservice.model.BProdApplyDetails;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ConfirmShowVO implements Serializable {

    @ApiModelProperty("订单信息")
    ConfirmShowTwoDTO inventoryDetailsDTO;

    @ApiModelProperty("商品列表")
    List<ConfirmListTwoDTO> confirmDTOList;

}
