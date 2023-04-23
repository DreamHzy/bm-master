package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ShowVO {

    @ApiModelProperty("通知新")
    private List<NoticeVO> noticeRes;

    @ApiModelProperty("轮播图")
    private List<BannerVO> bannerRes;
}
