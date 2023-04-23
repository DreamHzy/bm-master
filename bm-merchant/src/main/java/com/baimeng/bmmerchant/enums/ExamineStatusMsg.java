package com.baimeng.bmmerchant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExamineStatusMsg {//   '0审核中 1审核通过 2撤回 3驳回 4 已完成',


    TO_PUNCH_IN("0", "审核中"),
    TO_PUNCH_ONE("1", "审核通过"),
    TO_BE_COMPLETED("2", "撤回"),
    COMPLETED("3", "驳回"),
    COMPLETED_FOUR("4", "已完成");

    public static String getStatusDesc(String id) {
        for (ExamineStatusMsg newOrderRebateStatusEnum : ExamineStatusMsg.values()) {
            if (newOrderRebateStatusEnum.getId().equals(id)) {
                return newOrderRebateStatusEnum.getName();
            }
        }
        return "";
    }


    private String id;
    private String name;
}
