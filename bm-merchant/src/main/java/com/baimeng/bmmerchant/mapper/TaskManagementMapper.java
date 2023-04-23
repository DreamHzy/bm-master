package com.baimeng.bmmerchant.mapper;

import com.baimeng.bmmerchant.model.dto.TaskListDTO;
import com.baimeng.bmmerchant.model.dto.TaskStateDTO;
import com.baimeng.bmmerchant.model.dto.TodayDTO;
import com.baimeng.bmmerchant.model.vo.TaskVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskManagementMapper {

    TaskVO selectStore(@Param(value = "date") String date, @Param(value = "storeNo") String storeNo);

    List<String> queryFiles(Integer evaluateFileId);

    TodayDTO selectToday(@Param(value = "date") String date, @Param(value = "storeNo") String storeNo);

    TaskStateDTO selectTaskState(@Param(value = "date") String date, @Param(value = "storeNo") String storeNo);

    List<TaskListDTO> selectDetails(@Param(value = "date") String date, @Param(value = "storeNo") String storeNo, @Param(value = "url") String url);

    Integer onDutyAmount(@Param(value = "date") String date, @Param(value = "storeNo") String storeNo);

    Integer storeSum(String storeNo);
}
