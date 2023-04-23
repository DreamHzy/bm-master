package com.baimeng.bmmerchant.service;

import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.comment.PageWrap;
import com.baimeng.bmmerchant.model.dto.ExamineAdministratorsListDTO;
import com.baimeng.bmmerchant.model.dto.ExamineShopownerListDTO;
import com.baimeng.bmmerchant.model.vo.ExamineAdministratorsVO;
import com.baimeng.bmmerchant.model.vo.ExamineShopownerVO;

public interface ExamineService {
    ApiRes examineGoods(Integer prodApplyId, JeeUserDetails jeeUserDetails);

    ApiRes<ExamineAdministratorsVO> examineAdministratorsList(JeeUserDetails jeeUserDetails, PageWrap<ExamineAdministratorsListDTO> pageWrap);

    ApiRes examinePass(JeeUserDetails jeeUserDetails, Integer prodApplyId);

    ApiRes shopownePass(JeeUserDetails jeeUserDetails, Integer prodApplyId);

    ApiRes<ExamineShopownerVO> examineShopownerList(JeeUserDetails jeeUserDetails, PageWrap<ExamineShopownerListDTO> pageWrap);
}
