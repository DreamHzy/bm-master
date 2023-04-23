package com.baimeng.bmmerchant.mapper;

import com.baimeng.bmmerchant.model.vo.DistributionTaskListDetailVO;
import com.baimeng.bmmerchant.model.vo.QueryTaskBySceneIdVO;
import com.baimeng.bmmerchant.model.vo.TaskDetailListVO;
import com.baimeng.bmmerchant.model.vo.TasklistVO;
import com.baimeng.bmservice.model.BTaskStage;
import com.baimeng.bmservice.model.BTaskStageUser;
import com.baimeng.bmservice.model.BTaskUserClock;
import com.baimeng.bmservice.model.BTaskUserClockTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreTaskMapper {

    List<BTaskStageUser> queryList(@Param("sysUserId") Integer sysUserId, @Param("bTaskStageList") List<BTaskStage> bTaskStageList, @Param("day") String day);

    List<BTaskUserClockTask> queryBTaskUserClockTaskList(@Param("bTaskUserClockList")List<BTaskUserClock> bTaskUserClockList);

    List<TasklistVO> tasklistVOList(@Param("day") String day, @Param("sysUserId") Integer sysUserId,@Param("url") String url,@Param("storeNo") String storeNo);

    List<TaskDetailListVO> taskDetailListVOS(@Param("clockId") String clockId);

    List<DistributionTaskListDetailVO> distributionTaskListDetailVOS(@Param("storeNo") String storeNo,@Param("sysUserId") Integer sysUserId);

    List<QueryTaskBySceneIdVO> queryTaskBySceneId(@Param("userId")String userId, @Param("sceneId")String sceneId, @Param("stageId")String stageId,@Param("storeNo")String storeNo);
}
