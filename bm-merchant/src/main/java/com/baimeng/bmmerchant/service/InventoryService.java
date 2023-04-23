package com.baimeng.bmmerchant.service;

import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmcore.model.security.JeeUserDetails;
import com.baimeng.bmmerchant.model.dto.*;
import com.baimeng.bmmerchant.model.vo.ApplyIdVO;
import com.baimeng.bmmerchant.model.vo.*;

public interface InventoryService {

    ApiRes<AdminInventoryVO> adminInventory(AdminInventoryDTO inventoryDTO);

//    ApiRes<CategoryProdVO> discrepancy(AdminInventoryDTO inventoryDTO);

    ApiRes<CategoryProdsVO> search(String prodName);

    ApiRes<DetailsVO> details(DdetailsDTO detailsDTO);

    ApiRes<CategoryProdVO> enterDepot(AdminInventoryDTO inventoryDTO);

    ApiRes<ApplyIdVO> confirm(ConfirmListDTO confirmListDTO, JeeUserDetails jeeUserDetails);

    ApiRes<ConfirmShowVO> confirmShow(Integer prodApplyId);

    ApiRes updateApply(UpdateApplyDTO applyDTO);

    ApiRes addProd(AddProdDTO applyDTO);

    ApiRes<ChooseProdVO> chooseProd(ChooseProdDTO chooseProdDTO);

}
