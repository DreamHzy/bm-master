package com.baimeng.bmmerchant.model.vo;

import com.baimeng.bmmerchant.model.dto.CategoryProdDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CategoryProdVO implements Serializable {

    @ApiModelProperty("商品类别id")
    private String prodCategoryId;

    @ApiModelProperty("类别名称")
    private String name;

    @ApiModelProperty("商品列表")
    List<CategoryProdDTO> prodDTOList;


}
