package com.baiyi.caesar.service.datasource;

import com.baiyi.caesar.domain.generator.caesar.DatasourceInstance;
import com.baiyi.caesar.domain.param.datasource.DsInstanceParam;

import java.util.List;

/**
 * @Author baiyi
 * @Date 2021/5/19 6:07 下午
 * @Version 1.0
 */
public interface DsInstanceService {

    DatasourceInstance getById(Integer id);

    DatasourceInstance getByUuid(String uuid);

    List<DatasourceInstance> queryByParam(DsInstanceParam.DsInstanceQuery query);

    void add(DatasourceInstance datasourceInstance);

    void update(DatasourceInstance datasourceInstance);

    int countByConfigId(Integer configId);
}
