package com.baimeng.bmmerchant.mapper;

import com.baimeng.bmmerchant.model.dto.AllTaskDTO;
import com.baimeng.bmmerchant.model.dto.TaskListDTO;
import com.baimeng.bmmerchant.model.dto.TaskStateDTO;
import com.baimeng.bmmerchant.model.dto.TodayDTO;
import com.baimeng.bmmerchant.model.vo.StaffDetailVO;
import com.baimeng.bmmerchant.model.vo.StaffUserIdDTO;
import com.baimeng.bmmerchant.model.vo.StoreListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManageTaskMapper {

    List<StoreListVO> storeList(@Param("sysUserId") Integer sysUserId);

    List<TaskListDTO> selectCount(@Param("date")String storeNo, @Param("date")String date);

    TodayDTO selectTask(String date, String storeNo);

    AllTaskDTO selectAllTask(@Param(value = "date") String date, @Param(value = "list") List<String> list );

    List<String> selectUser(Integer sysUserId);

    List<TaskListDTO> selectTaskDetails(@Param(value = "date")String date, @Param(value = "list") List<String> list,@Param(value = "url")String url);

    AllTaskDTO selectStoreTask(String date, String storeNo);

    List<TaskListDTO> selectStoreDetails(@Param(value = "date")String date, @Param(value = "storeNo")String storeNo,@Param(value = "url")String url);

    String selectDetails(String storeNo);

    List<String> onDutyAmount(Integer sysUserId);

    Integer onDutyAmounts(String date, List<String> onDutyAmount);

    Integer storeSum(@Param(value = "onDutyAmount") List<String> onDutyAmount);

    TaskStateDTO selectTaskState(@Param(value = "date")String date,@Param(value = "onDutyAmount") List<String> onDutyAmount);

    List<TaskListDTO> selectDetailsList(@Param(value = "date")String date,@Param(value = "onDutyAmount") List<String> onDutyAmount, @Param(value = "url")String url);

    Integer selectDutyAmount(String date, String storeNo);

    Integer storeAllSum(String storeNo);

    TaskStateDTO selectTaskstatus(String date, String storeNo);

    List<TaskListDTO> selectDetailsLists(String date, String storeNo, String url);

    List<StaffDetailVO> staffDetailVOS(@Param("day") String day, @Param("storeNo") String storeNo, @Param("sysUserId") Integer sysUserId);

    List<StaffUserIdDTO> staffUserID(@Param("stores") List<String> stores);
}
