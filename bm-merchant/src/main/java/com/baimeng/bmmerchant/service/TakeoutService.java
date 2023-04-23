package com.baimeng.bmmerchant.service;

import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.comment.PageWrap;
import com.baimeng.bmmerchant.model.dto.*;
import com.baimeng.bmmerchant.model.vo.TakeoutItemVO;
import com.baimeng.bmmerchant.model.vo.VisitDetailsVO;
import com.baimeng.bmmerchant.model.vo.VisitListVO;

public interface TakeoutService {

    ApiRes countTakeout(TakeoutDTO takeoutDTO, JeeUserDetails jeeUserDetails);

    ApiRes<VisitListVO> visitListSerive(VisitListDTO visitListDTO, JeeUserDetails jeeUserDetails);

    ApiRes<VisitDetailsVO> takeoutVisitDetailsService(PageWrap<VisitDetailsDTO> pageWrap,JeeUserDetails jeeUserDetails);

    ApiRes<TakeoutItemVO> takeoutVisitItemService(VisitItemDTO visitItemDTO);

    ApiRes<Integer> number(JeeUserDetails jeeUserDetails, NumberDTO numberDTO);

    ApiRes<VisitListVO> adminTakeout(JeeUserDetails jeeUserDetails, AdminDTO adminDTO);

    ApiRes<VisitListVO> admintakeoutDetails(JeeUserDetails jeeUserDetails, PageWrap<AdminDetailsDTO> pageWrap);
}
