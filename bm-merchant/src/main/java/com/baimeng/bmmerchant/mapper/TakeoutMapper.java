package com.baimeng.bmmerchant.mapper;

import com.baimeng.bmmerchant.model.dto.TakeoutVisitItemDTO;
import com.baimeng.bmmerchant.model.dto.VisitDetailsListDTO;
import com.baimeng.bmmerchant.model.vo.VisitListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TakeoutMapper {

    VisitListVO selectVisitList(@Param(value = "date") String date, @Param(value = "storeNo") String storeNo);

    List<VisitDetailsListDTO> selectVisitDetails(@Param(value = "takeoutPlatform")String takeoutPlatform,@Param(value = "storeNo") String storeNo, @Param(value = "date")String date);

    String selectSum(@Param(value = "takeoutPlatform") String takeoutPlatform, @Param(value = "storeNo") String storeNo, @Param(value = "date") String date);

//    Integer selectMax(@Param(value = "takeoutDate") String takeoutDate, @Param(value = "storeNo") String storeNo,@Param(value = "takeoutPlatform") Byte takeoutPlatform);

//    TakeoutVisitItemDTO selectItem(VisitItemDTO visitItemDTO);

    List<String> selectSysUser(@Param(value = "sysUserId") Integer sysUserId);

    VisitListVO selectAdminSum(@Param(value = "storeNumberList") List<String> storeNumberList, @Param(value = "date") String date);

    List<VisitDetailsListDTO> selectAdminDetails(@Param(value = "date")String date,@Param(value = "takeoutPlatform") String takeoutPlatform,@Param(value = "storeNumberList") List<String> storeNumberList);

    String selectAdminAmount(@Param(value = "date")String date,@Param(value = "takeoutPlatform") String takeoutPlatform,@Param(value = "storeNumberList") List<String> storeNumberList);

    Integer selectMax(String takeoutDate, Byte takeoutPlatform, String storeNo);

    TakeoutVisitItemDTO selectItem(@Param(value = "newtakeoutId") Integer newtakeoutId);
}
