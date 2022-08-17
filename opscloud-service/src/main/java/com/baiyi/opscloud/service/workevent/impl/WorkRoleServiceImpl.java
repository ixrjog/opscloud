package com.baiyi.opscloud.service.workevent.impl;

import com.baiyi.opscloud.domain.generator.opscloud.WorkRole;
import com.baiyi.opscloud.mapper.opscloud.WorkRoleMapper;
import com.baiyi.opscloud.service.workevent.WorkRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 修远
 * @Date 2022/8/5 5:06 PM
 * @Since 1.0
 */

@Service
@RequiredArgsConstructor
public class WorkRoleServiceImpl implements WorkRoleService {

    private final WorkRoleMapper workRoleMapper;

    @Override
    public List<WorkRole> listAll() {
        return workRoleMapper.selectAll();
    }

    @Override
    public WorkRole getById(Integer id) {
        return workRoleMapper.selectByPrimaryKey(id);
    }
}
