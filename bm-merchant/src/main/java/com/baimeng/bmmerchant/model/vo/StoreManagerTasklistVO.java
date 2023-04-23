package com.baimeng.bmmerchant.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class StoreManagerTasklistVO {

    @ApiModelProperty("员工编号")
    private String sysNo;

    @ApiModelProperty("姓名")
    private String sysName;

    @ApiModelProperty("任务相关信息")
    private List<TasklistVO> tasklistVOList;

    @ApiModelProperty("星级")
    private String score;

    @ApiModelProperty("评价内容")
    private String msg;

    @ApiModelProperty("图片列表")
    private List<String> imagList;



    private String ossUrl;

}
