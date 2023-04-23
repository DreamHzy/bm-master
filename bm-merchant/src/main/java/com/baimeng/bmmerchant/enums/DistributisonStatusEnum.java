package com.baimeng.bmmerchant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xinchen
 * @date 2021/11/17 11:52
 * @description:
 */
@Getter
@AllArgsConstructor
public enum DistributisonStatusEnum { //1-正常 2待生效 3待失效
    TO_PUNCH_IN("1", "正常"),
    TO_BE_COMPLETED("2", "待生效"),
    COMPLETED("3", "待失效");


    public static String getStatusDesc(String id) {
        for (DistributisonStatusEnum newOrderRebateStatusEnum : DistributisonStatusEnum.values()) {
            if (newOrderRebateStatusEnum.getId().equals(id)) {
                return newOrderRebateStatusEnum.getName();
            }
        }
        return "";
    }


    private String id;
    private String name;
}
