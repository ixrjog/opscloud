package com.baiyi.opscloud.leo.message.handler.impl;

import com.baiyi.opscloud.common.util.BeanCopierUtil;
import com.baiyi.opscloud.domain.DataTable;
import com.baiyi.opscloud.domain.generator.opscloud.LeoBuild;
import com.baiyi.opscloud.domain.generator.opscloud.LeoJob;
import com.baiyi.opscloud.domain.param.leo.request.QueryLeoBuildLeoRequestParam;
import com.baiyi.opscloud.domain.param.leo.request.type.LeoRequestType;
import com.baiyi.opscloud.domain.vo.leo.LeoBuildVO;
import com.baiyi.opscloud.leo.message.handler.base.BaseLeoContinuousDeliveryRequestHandler;
import com.baiyi.opscloud.leo.packer.LeoBuildResponsePacker;
import com.baiyi.opscloud.service.leo.LeoBuildService;
import com.baiyi.opscloud.service.leo.LeoJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.websocket.Session;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author baiyi
 * @Date 2022/11/24 17:22
 * @Version 1.0
 */
@Slf4j
@Component
public class QueryLeoBuildRequestHandler extends BaseLeoContinuousDeliveryRequestHandler<QueryLeoBuildLeoRequestParam> {

    @Resource
    private LeoBuildService leoBuildService;

    @Resource
    private LeoJobService leoJobService;

    @Resource
    private LeoBuildResponsePacker leoBuildResponsePacker;

    @Override
    public String getMessageType() {
        return LeoRequestType.QUERY_LEO_BUILD.name();
    }

    @Override
    public void handleRequest(Session session, String message) {
        QueryLeoBuildLeoRequestParam queryParam = toRequestParam(message);
        List<Integer> jobIds = leoJobService.querJobWithApplicationIdAndEnvType(queryParam.getApplicationId(), queryParam.getEnvType()).stream()
                .map(LeoJob::getId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(jobIds))
            return;
        queryParam.setJobIds(jobIds);
        DataTable<LeoBuildVO.Build> dataTable = queryLeoBuildPage(queryParam);
        sendToSession(session, dataTable);
    }

    public DataTable<LeoBuildVO.Build> queryLeoBuildPage(QueryLeoBuildLeoRequestParam pageQuery) {
        DataTable<LeoBuild> table = leoBuildService.queryBuildPage(pageQuery);
        List<LeoBuildVO.Build> data = BeanCopierUtil.copyListProperties(table.getData(), LeoBuildVO.Build.class).stream()
                .peek(leoBuildResponsePacker::wrap)
                .collect(Collectors.toList());
        return new DataTable<>(data, table.getTotalNum());
    }

    private QueryLeoBuildLeoRequestParam toRequestParam(String message) {
        return toLeoRequestParam(message, QueryLeoBuildLeoRequestParam.class);
    }

}
