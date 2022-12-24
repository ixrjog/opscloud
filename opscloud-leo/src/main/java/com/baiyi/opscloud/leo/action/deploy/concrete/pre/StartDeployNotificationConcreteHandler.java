package com.baiyi.opscloud.leo.action.deploy.concrete.pre;

import com.baiyi.opscloud.domain.generator.opscloud.LeoDeploy;
import com.baiyi.opscloud.leo.action.deploy.BaseDeployHandler;
import com.baiyi.opscloud.leo.action.deploy.base.BaseDeployStrategy;
import com.baiyi.opscloud.leo.action.deploy.base.DeployStrategyFactory;
import com.baiyi.opscloud.leo.action.deploy.base.IDeployStep;
import com.baiyi.opscloud.leo.constants.DeployStepConstants;
import com.baiyi.opscloud.leo.domain.model.LeoDeployModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author baiyi
 * @Date 2022/12/8 11:03
 * @Version 1.0
 */
@Slf4j
@Component
public class StartDeployNotificationConcreteHandler extends BaseDeployHandler implements IDeployStep {

    /**
     * 部署开始通知
     *
     * @param leoDeploy
     * @param deployConfig
     */
    @Override
    protected void handle(LeoDeploy leoDeploy, LeoDeployModel.DeployConfig deployConfig) {
        BaseDeployStrategy deployStrategy = DeployStrategyFactory.getStrategy(getStep(), deployConfig.getDeploy().getDeployType());
        // 基于策略模式实现
        deployStrategy.handleRequest(leoDeploy, deployConfig);
    }

    @Override
    public String getStep() {
        return DeployStepConstants.START_NOTIFICATION.name();
    }

}