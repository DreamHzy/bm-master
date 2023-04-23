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
public enum StaffDetailEnum { //"0未打 1正常 2逾期 3早退"
    TO_PUNCH_IN("0", "未打"),
    TO_PUNCH_ONE("1", "正常"),
    TO_BE_COMPLETED("2", "迟到"),
    COMPLETED("3", "早退");


    public static String getStatusDesc(String id) {
        for (StaffDetailEnum newOrderRebateStatusEnum : StaffDetailEnum.values()) {
            if (newOrderRebateStatusEnum.getId().equals(id)) {
                return newOrderRebateStatusEnum.getName();
            }
        }
        return "";
    }


    private String id;
    private String name;
}
