package com.baimeng.bmmerchant.service;

import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.model.dto.*;
import com.baimeng.bmmerchant.model.vo.CategoryVO;
import com.baimeng.bmmerchant.model.vo.ContenctVO;


public interface InspectService {

    ApiRes<CategoryVO> queryCategoryService(CategoryDTO categoryDTO, JeeUserDetails jeeUserDetails);

    ApiRes saveAssessService(InspectListDTO inspectDTOList,JeeUserDetails jeeUserDetails);

    ApiRes<ContenctVO> queryAssessService(AsseaaDTO asseaaDTO,  JeeUserDetails jeeUserDetails );

    ApiRes updateAssess(InspectsDTO inspectsDTO, JeeUserDetails jeeUserDetails);

    ApiRes<CategoryVO> adminQueryCategory(AdminCategoryDTO categoryDTO, JeeUserDetails jeeUserDetails);

    ApiRes updateOrAddAssess(UpdateOrAddAssessDTO updateOrAddAssessDTO, JeeUserDetails jeeUserDetails);
}
