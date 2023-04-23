package com.baimeng.bmservice.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 考勤配置
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BCheckWorkAttendanceConfig implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "attendance_config_id", type = IdType.AUTO)
    private Integer attendanceConfigId;

    /**
     * 门店编号
     */
    private String storeNo;

    /**
     * 图片
     */
    private String image;

    /**
     * 小图标
     */
    private String icon;

    /**
     * 名称
     */
    private String name;

    /**
     * 上班打卡时间
     */
    private Date startTime;

    /**
     * 下班打卡时间
     */
    private Date endTime;

    /**
     * 排序
     */
    private Integer num;

    /**
     * 0停用 1启用
     */
    private Byte state;

    private Date createdAt;

    private Date updatedAt;


}
