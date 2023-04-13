package com.baiyi.opscloud.domain.param.datasource;

import com.baiyi.opscloud.domain.param.IExtend;
import com.baiyi.opscloud.domain.param.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author baiyi
 * @Date 2021/6/15 11:19 上午
 * @Version 1.0
 */
public class DsAccountGroupParam {

    @Data
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @Schema
    public static class GroupPageQuery extends PageParam implements IExtend {

        @Schema(name = "实例id")
        @NotNull
        private Integer instanceId;

        private String accountUid;

        @Schema(name = "模糊查询")
        private String queryName;

        @Schema(name = "展开")
        private Boolean extend;

        @Schema(name = "是否激活")
        private Boolean isActive;

    }

}