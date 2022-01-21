package com.baiyi.opscloud.common.datasource;

import com.baiyi.opscloud.common.datasource.base.BaseConfig;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author baiyi
 * @Date 2021/10/21 4:35 下午
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AwsConfig extends BaseConfig {

    private Aws aws;

    @Data
    @NoArgsConstructor
    @ApiModel
    public static class Aws {
        private Account account;
        private String apRegionId;
        private Ec2 ec2;
    }

    @Data
    @NoArgsConstructor
    @ApiModel
    public static class Account {

        private String id;
        private String name;
        private String company; // 可选项公司
        private String accessKeyId;
        private String secretKey;

    }

    @Data
    @NoArgsConstructor
    @ApiModel
    public static class Ec2 {

        private String instances;

    }

}

