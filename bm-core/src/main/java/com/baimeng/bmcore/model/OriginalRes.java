package com.baimeng.bmcore.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * 返回原始数据
 *
 * @author terrfly
 * @site https://www.jeequan.com
 * @date 2021/6/8 16:37
 */
@Data
@AllArgsConstructor
public class OriginalRes {

    /** 返回数据 **/
    private Object data;

    public static OriginalRes ok(Object data){
        return new OriginalRes(data);
    }
}
