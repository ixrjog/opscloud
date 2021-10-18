package com.baiyi.opscloud.datasource.nexus.base;

import com.baiyi.opscloud.BaseUnit;
import com.baiyi.opscloud.common.datasource.NexusDsInstanceConfig;
import com.baiyi.opscloud.common.datasource.ZabbixDsInstanceConfig;
import com.baiyi.opscloud.datasource.factory.DsConfigFactory;
import com.baiyi.opscloud.domain.generator.opscloud.DatasourceConfig;
import com.baiyi.opscloud.service.datasource.DsConfigService;

import javax.annotation.Resource;

/**
 * @Author baiyi
 * @Date 2021/10/15 1:58 下午
 * @Version 1.0
 */
public class BaseNexusTest extends BaseUnit {

    @Resource
    private DsConfigService dsConfigService;

    @Resource
    private DsConfigFactory dsFactory;

    protected NexusDsInstanceConfig getConfig() {
        DatasourceConfig datasourceConfig = dsConfigService.getById(12);
        return dsFactory.build(datasourceConfig, NexusDsInstanceConfig.class);
    }
}