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
public enum TaskClockEnum {
    TO_PUNCH_IN("0", "待打卡"),
    TO_BE_COMPLETED("1", "待完成"),
    COMPLETED("2", "已完成"),
    COMPLETED_OVERDUE("3", "已完成(逾期)");


    public static String getStatusDesc(String id) {
        for (TaskClockEnum newOrderRebateStatusEnum : TaskClockEnum.values()) {
            if (newOrderRebateStatusEnum.getId().equals(id)) {
                return newOrderRebateStatusEnum.getName();
            }
        }
        return "";
    }


    private String id;
    private String name;
}
