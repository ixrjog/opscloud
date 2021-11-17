package com.baiyi.opscloud.datasource.jenkins.provider;

import com.baiyi.opscloud.common.annotation.SingleTask;
import com.baiyi.opscloud.common.datasource.JenkinsConfig;
import com.baiyi.opscloud.common.constant.enums.DsTypeEnum;
import com.baiyi.opscloud.core.factory.AssetProviderFactory;
import com.baiyi.opscloud.datasource.jenkins.convert.ComputerAssetConvert;
import com.baiyi.opscloud.datasource.jenkins.datasource.JenkinsServerDatasource;
import com.baiyi.opscloud.core.model.DsInstanceContext;
import com.baiyi.opscloud.core.provider.asset.BaseAssetProvider;
import com.baiyi.opscloud.core.util.AssetUtil;
import com.baiyi.opscloud.domain.builder.asset.AssetContainer;
import com.baiyi.opscloud.domain.generator.opscloud.DatasourceConfig;
import com.baiyi.opscloud.domain.generator.opscloud.DatasourceInstance;
import com.baiyi.opscloud.domain.generator.opscloud.DatasourceInstanceAsset;
import com.baiyi.opscloud.domain.types.DsAssetTypeEnum;
import com.google.common.collect.Lists;
import com.offbytwo.jenkins.model.Computer;
import com.offbytwo.jenkins.model.ComputerWithDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.baiyi.opscloud.common.constant.SingleTaskConstants.PULL_JENKINS_COMPUTER;

/**
 * @Author baiyi
 * @Date 2021/7/2 9:59 上午
 * @Version 1.0
 */
@Component
public class JenkinsComputerProvider extends BaseAssetProvider<ComputerWithDetails> {

    @Resource
    private JenkinsComputerProvider jenkinsComputerProvider;

    @Override
    public String getInstanceType() {
        return DsTypeEnum.JENKINS.name();
    }

    @Override
    public String getAssetType() {
        return DsAssetTypeEnum.JENKINS_COMPUTER.getType();
    }

    private JenkinsConfig.Jenkins buildConfig(DatasourceConfig dsConfig) {
        return dsConfigHelper.build(dsConfig, JenkinsConfig.class).getJenkins();
    }

    @Override
    protected List<ComputerWithDetails> listEntries(DsInstanceContext dsInstanceContext) {
        try {
            Map<String, Computer> computerMap = JenkinsServerDatasource.getComputers(buildConfig(dsInstanceContext.getDsConfig()));
            List<ComputerWithDetails> computerWithDetails = Lists.newArrayList();
            for (String k : computerMap.keySet())
                computerWithDetails.add(computerMap.get(k).details());
            return computerWithDetails;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("查询条目失败");
    }

    @Override
    @SingleTask(name = PULL_JENKINS_COMPUTER, lockTime = "5m")
    public void pullAsset(int dsInstanceId) {
        doPull(dsInstanceId);
    }

    @Override
    protected boolean equals(DatasourceInstanceAsset asset, DatasourceInstanceAsset preAsset) {
        if (!AssetUtil.equals(preAsset.getName(), asset.getName()))
            return false;
        if (preAsset.getIsActive() != asset.getIsActive())
            return false;
        return true;
    }

    @Override
    protected AssetContainer toAssetContainer(DatasourceInstance dsInstance, ComputerWithDetails entry) {
        return ComputerAssetConvert.toAssetContainer(dsInstance, entry);
    }

    @Override
    public void afterPropertiesSet() {
        AssetProviderFactory.register(jenkinsComputerProvider);
    }
}