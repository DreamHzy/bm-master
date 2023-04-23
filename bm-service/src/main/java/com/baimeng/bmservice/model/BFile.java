package com.baimeng.bmservice.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文件存储表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("b_file")
public class BFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "notice_file_id", type = IdType.AUTO)
    private Integer noticeFileId;

    /**
     * 归属关联id
     */
    private String belongInfoId;

    private String belongInfo;

    /**
     * 1、excel表格 2、word 3、视频 4、照片
     */
    private Integer type;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 地址
     */
    private String url;

    /**
     * 0-停用, 1-正常
     */
    private Byte state;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


    //gw
    public static final LambdaQueryWrapper<BFile> gw() {
        return new LambdaQueryWrapper<>();
    }
}
