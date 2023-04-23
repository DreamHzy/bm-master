package com.baimeng.bmmerchant.mapper;

import com.baimeng.bmmerchant.model.dto.*;
import com.baimeng.bmmerchant.model.vo.AdminInventoryVO;
import com.baimeng.bmmerchant.model.vo.CategoryProdsVO;
import com.baimeng.bmmerchant.model.vo.ChooseProdVO;
import com.baimeng.bmmerchant.model.vo.ProdWriteStockVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InventoryMapper {
    List<String> selectAdminInventory(@Param(value = "sysUserId")Integer sysUserId);

    List<AdminInventoryVO> selectAdminCategory(String storeNo, String url);

    String selectTest(@Param(value = "createTime")String createTime, @Param(value = "startDate")String startDate, @Param(value = "prodId")String prodId);

    List<ProdAverageDTO> selectTests();

    List<CategoryNameDTO>  selectInventory(String storeNo);

    List<CategoryProdDTO>  selectCategoryProd(String storeNo,String url);

    List<CategoryProdsVO>  selectName(@Param(value = "prodName") String prodName,@Param(value = "url")String url);

    AdminInventoryVO selectDetails(@Param(value = "prodId") String prodId, @Param(value = "url")String url);

    List<InventoryDetailsDTO> selectDetailsList(@Param(value = "prodId") String prodId,@Param(value = "createTime") String createTime,@Param(value = "endTime") String endTime,@Param(value = "url") String url);

    ConfirmShowTwoDTO selectProdApply(Integer prodApplyId);

    List<ConfirmListTwoDTO> selectConfirmList(Integer prodApplyId,String url);

    Integer selectById(Integer prodApplyDetailsId);

    List<ChooseProdVO> selectChooseProd(@Param(value = "integerList") List<Integer> integerList,@Param("prodName") String prodName);

    List<Integer> selectNotInId(Integer applyId);

    String selectQuery(String prodId);

    ProdWriteStockVO prodWriteStockVOS(String prodId );
}
