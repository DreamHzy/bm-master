package com.baimeng.bmservice.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 打卡详情
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BCheckWorkAttendanceDeatil implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "attendance_deatil_id", type = IdType.AUTO)
    private Integer attendanceDeatilId;

    /**
     * 打卡配置表id
     */
    private Integer attendanceConfigId;

    /**
     * 门店编号
     */
    private String storeNo;

    private Integer sysUserId;

    /**
     * 0正常打卡 1逾期打卡2早退
     */
    private Byte state;

    /**
     * 打卡时间
     */
    private Date createdAt;

    private Date createdDay;

    private String address;


    //gw
    public static final LambdaQueryWrapper<BCheckWorkAttendanceDeatil> gw() {
        return new LambdaQueryWrapper<>();
    }
}
