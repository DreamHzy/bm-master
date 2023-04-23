package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class VisitListVO implements Serializable {

    @ApiModelProperty("外卖条数")
    private String takeoutNumber;

    @ApiModelProperty("返利总额")
    private String rebateSum;

    @ApiModelProperty("外卖返利")
    private String returnMoney;

    @ApiModelProperty("口味：0正常")
    private String normalTaste ;

    @ApiModelProperty("口味：1口味太淡 ")
    private String tasteTooLight ;

    @ApiModelProperty("口味：2口味太辣 ")
    private String tasteToHot ;

    @ApiModelProperty("口味：3太咸")
    private String tasteToSalty;

    @ApiModelProperty("分量：0正常")
    private String normal;

    @ApiModelProperty("分量：1鱼太少")
    private String fishFew;

    @ApiModelProperty("分量：2菜太少")
    private String vegetablesFew;

    @ApiModelProperty("异物：0无")
    private String noException ;

    @ApiModelProperty("异物：1有异物")
    private String haveException;

    @ApiModelProperty("异物：2有头发")
    private String haveHair;

    @ApiModelProperty("异物：3有蟑螂")
    private String havecockroach;

    @ApiModelProperty("少漏(0无)")
    private String omissionNull;

    @ApiModelProperty("少漏(1少主食)")
    private String omissionMain;

    @ApiModelProperty("少漏(2少餐具)")
    private String omissionTableware;

    @ApiModelProperty("电话反感:0是")
    private String phoneDisgustYes;

    @ApiModelProperty("电话反感:1否")
    private String phoneDisgustNo;

    @ApiModelProperty("好评意向：0是")
    private String goodIntentionsYes;

    @ApiModelProperty("好评意向：1否")
    private String goodIntentionsNo;

    @ApiModelProperty("饿了么总条数")
    private Integer elmTotalNumber;

    @ApiModelProperty("美团总条数")
    private Integer mtTotalNumber;

}
