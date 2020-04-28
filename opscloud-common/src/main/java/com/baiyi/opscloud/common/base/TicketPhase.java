package com.baiyi.opscloud.common.base;

/**
 * @Author baiyi
 * @Date 2020/4/27 3:58 下午
 * @Version 1.0
 */
public enum  TicketPhase {

    CREATED("CREATED_TICKET"), // 新建
    APPLIED("APPLIED_TICKET"), // 提交申请
    UNDER_APPROVAL("UNDER_APPROVAL"), // 在审批
    CONFIGURATION("CONFIGURATION"), // 运维配置阶段
    FINALIZED("FINALIZED");  // 结束

    private String phase;

    TicketPhase(String phase) {
        this.phase = phase;
    }

    public String getPhase() {
        return this.phase;
    }
}
