package com.baimeng.bmmerchant.mapper;

import com.baimeng.bmmerchant.model.vo.UserTaskStageVO;
import com.baimeng.bmservice.model.BTaskStageUser;
import com.baimeng.bmservice.model.BTaskUserClock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserTaskMapper {
    List<UserTaskStageVO> queryList();

    List<BTaskStageUser> bTaskStageUserList(@Param("list") List<BTaskUserClock> bTaskUserClockList);

    void updateTaskStageUserEffect();

    void updateTaskStageUserInvalid();
}
