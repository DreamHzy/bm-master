package com.baimeng.bmmerchant.model.vo;

import com.baimeng.bmmerchant.comment.PageData;
import lombok.Data;

@Data
public class ExamineAdministratorsVO {
    private String waitAuditNum;

    private PageData<ExamineAdministratorsListVO> examineAdministratorsListVOPageData;
}
