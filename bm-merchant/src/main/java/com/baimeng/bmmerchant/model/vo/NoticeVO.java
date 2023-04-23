package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NoticeVO {
    @ApiModelProperty("通知id")
    private String noticeId;

    @ApiModelProperty("图片地址")
    private String noticeUrl;
}
