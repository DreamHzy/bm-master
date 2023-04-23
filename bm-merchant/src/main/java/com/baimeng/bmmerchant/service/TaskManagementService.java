package com.baimeng.bmmerchant.service;

import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.model.dto.EvaluateDTO;
import com.baimeng.bmmerchant.model.dto.TaskManagementDTO;
import com.baimeng.bmmerchant.model.vo.StoreManagerTasklistVO;
import com.baimeng.bmmerchant.model.vo.TaskVO;

public interface TaskManagementService {

    ApiRes<TaskVO> taskManagementService(TaskManagementDTO taskDTO, JeeUserDetails jeeUserDetails);

    ApiRes<StoreManagerTasklistVO> storeManagerTasklist(String day, String userId);

    ApiRes evaluate(EvaluateDTO evaluateDTO,JeeUserDetails jeeUserDetails);
}

