package com.baimeng.bmmerchant.model.vo;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

@Data
public class UserTaskStageVO {

    private Integer stageId;


    private Integer sysUserId;

    private String storeNo;

    private Date endTime;

    private Date startTime;


}
