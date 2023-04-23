package com.baimeng.bmmerchant.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MtSysEntitlementMapper {
    int userHasLeftMenu(@Param("userId") Integer userId, @Param("sysType") String sysType);

    List<String> selectEntIdsByUserId(@Param("userId") Integer userId,  @Param("sysType")String sysType);
}
