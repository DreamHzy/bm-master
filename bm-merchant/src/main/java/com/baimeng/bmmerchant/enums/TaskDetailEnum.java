package com.baimeng.bmmerchant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskDetailEnum {

    TO_PUNCH_IN("0", "待完成"),
    TO_BE_COMPLETED("1", "正常完成"),
    COMPLETED("2", "已完成(逾期)");


    public static String getStatusDesc(String id) {
        for (TaskDetailEnum newOrderRebateStatusEnum : TaskDetailEnum.values()) {
            if (newOrderRebateStatusEnum.getId().equals(id)) {
                return newOrderRebateStatusEnum.getName();
            }
        }
        return "";
    }

    private String id;
    private String name;
}
