package com.baimeng.bmmerchant.mapper;

import com.baimeng.bmmerchant.model.dto.CategoryProdDTO;
import com.baimeng.bmmerchant.model.dto.DdetailsDTO;
import com.baimeng.bmmerchant.model.dto.FileUrlDTO;
import com.baimeng.bmmerchant.model.dto.UpdateOrAddAssessDTO;
import com.baimeng.bmmerchant.model.vo.AdminInventoryVO;
import com.baimeng.bmmerchant.model.vo.CategoryVO;
import com.baimeng.bmmerchant.model.vo.ContenctDetailVO;
import com.baimeng.bmmerchant.model.vo.ContenctVO;
import com.baimeng.bmservice.model.BFile;
import com.baimeng.bmservice.model.BInspectEvaluate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InspectMapper {

    List<CategoryVO> selectCategory(@Param(value = "createDay") String createDay, @Param(value = "storeNo") String storeNo,@Param(value = "url") String url);

    List<FileUrlDTO> selectImage(@Param(value = "categoryId") String categoryId, @Param(value = "url") String url);

    List<ContenctVO> selectContenctId(String categoryId);

    List<ContenctVO> selectContenct(@Param(value = "evaluateId") String evaluateId);

    List<BFile> selectRemoveId(Integer evaluateId);

    List<ContenctDetailVO> contenctDetailVOList(@Param(value = "categoryId") Integer categoryId, @Param(value = "storeNo") String storeNo);

    List<BInspectEvaluate> queryList(@Param(value = "list")List<ContenctDetailVO> contenctDetailVOList,@Param(value = "day")String day);

    List<FileUrlDTO> queryFile(@Param(value = "list")List<BInspectEvaluate> bInspectEvaluateList, @Param(value = "url")String url);

    List<String> selectAdminCategory(@Param(value = "sysUserId")Integer sysUserId);

    List<CategoryVO> selectAdmin(@Param(value = "createDay")String createDay, @Param(value = "list")List<String> list, @Param(value = "url")String url);

    void updateByContenctIdAndCategoryIdAndDay(UpdateOrAddAssessDTO updateOrAddAssessDTO);

//    List<CategoryProdDTO> selectName(String prodName);

//    AdminInventoryVO selectDetails(DdetailsDTO detailsDTO);

}
