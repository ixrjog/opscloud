package com.baiyi.opscloud.mapper.opscloud;

import com.baiyi.opscloud.domain.generator.opscloud.WorkEvent;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface WorkEventMapper extends Mapper<WorkEvent>, InsertListMapper<WorkEvent> {
}