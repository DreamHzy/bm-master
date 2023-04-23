package com.baimeng.bmmerchant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplyTypeMsg {

    TO_PUNCH_IN("0", "出库"),
    TO_PUNCH_ONE("1", "支出");

    public static String getStatusDesc(String id) {
        for (ApplyTypeMsg newOrderRebateStatusEnum : ApplyTypeMsg.values()) {
            if (newOrderRebateStatusEnum.getId().equals(id)) {
                return newOrderRebateStatusEnum.getName();
            }
        }
        return "";
    }
    private String id;
    private String name;
}
