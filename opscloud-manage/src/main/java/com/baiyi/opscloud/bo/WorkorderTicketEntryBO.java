package com.baiyi.opscloud.bo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Author baiyi
 * @Date 2020/4/28 12:51 下午
 * @Version 1.0
 */
@Data
@Builder
public class WorkorderTicketEntryBO {

    private Integer id;
    private Integer workorderTicketId;
    private String name;
    @Builder.Default
    private Integer entryStatus = 0;
    private String comment;
    private String entryKey;
    private String entryResult;
    private Date createTime;
    private Date updateTime;
    private String entryDetail;
}
