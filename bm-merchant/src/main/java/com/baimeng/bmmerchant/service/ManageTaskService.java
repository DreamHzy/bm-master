package com.baimeng.bmmerchant.service;

import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.model.dto.TaskDTO;
import com.baimeng.bmmerchant.model.vo.StoreListVO;
import com.baimeng.bmmerchant.model.vo.TaskVO;

public interface ManageTaskService {

    ApiRes<StoreListVO> storeList(JeeUserDetails jeeUserDetails);

    ApiRes<TaskVO> taskList(TaskDTO taskDTO,JeeUserDetails jeeUserDetails);

}
