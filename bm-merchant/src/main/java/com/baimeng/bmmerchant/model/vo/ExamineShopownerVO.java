package com.baimeng.bmmerchant.model.vo;

import com.baimeng.bmmerchant.comment.PageData;
import lombok.Data;

import java.util.List;

@Data
public class ExamineShopownerVO {

    private String waitAuditNum;

    private String passAuditNum;

    private  PageData<ExamineShopownerListVO> examineShopownerListVOList;
}
