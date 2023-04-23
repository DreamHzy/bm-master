package com.baimeng.bmmerchant.mapper;

import com.baimeng.bmmerchant.model.vo.ExamineAdministratorsListVO;
import com.baimeng.bmmerchant.model.vo.ExamineShopownerListVO;
import com.baimeng.bmservice.model.BProd;
import com.baimeng.bmservice.model.BProdApplyDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamineMapper {
    List<BProd> listByProdIds(@Param("list") List<BProdApplyDetails> bProdApplyDetailsList);

    List<ExamineShopownerListVO> examineShopownerList(@Param("sysUserId")Integer sysUserId,@Param("storeNo")String storeNo,@Param("status") String status);

    List<String> selectSysUser(Integer sysUserId);

    List<ExamineAdministratorsListVO> examineAdministratorsList(@Param("list")List<String> storeNumberList, @Param("status") String status);

    Integer queryCountShopwner(@Param("sysUserId")Integer sysUserId,@Param("storeNo")String storeNo,@Param("status")Integer status);

    Integer queryCountAdmin(@Param("list")List<String> storeNumberList);
}
