package com.baiyi.opscloud.service.server.impl;

import com.baiyi.opscloud.domain.DataTable;
import com.baiyi.opscloud.domain.generator.OcServerGroup;
import com.baiyi.opscloud.domain.param.server.ServerGroupParam;
import com.baiyi.opscloud.mapper.OcServerGroupMapper;
import com.baiyi.opscloud.service.server.OcServerGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author baiyi
 * @Date 2020/1/8 10:13 上午
 * @Version 1.0
 */
@Service
public class OcServerGroupServiceImpl implements OcServerGroupService {
    @Resource
    private OcServerGroupMapper ocServerGroupMapper;

    @Override
    public int countByGrpType(int grpType){
        Example example = new Example(OcServerGroup.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("grpType",grpType);
        return ocServerGroupMapper.selectCountByExample(example);
    }

    @Override
    public OcServerGroup queryOcServerGroupById(Integer id) {
        return ocServerGroupMapper.selectByPrimaryKey(id);
    }

    @Override
    public OcServerGroup queryOcServerGroupByName(String name) {
        Example example = new Example(OcServerGroup.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", name);
        return ocServerGroupMapper.selectOneByExample(example);
    }

    @Override
    public DataTable<OcServerGroup> queryOcServerGroupByParam(ServerGroupParam.PageQuery pageQuery) {
        Page page = PageHelper.startPage(pageQuery.getPage(), pageQuery.getLength().intValue());
        List<OcServerGroup> ocServerGroupList = ocServerGroupMapper.queryOcServerGroupByParam(pageQuery);
        return new DataTable<>(ocServerGroupList, page.getTotal());
    }

    @Override
    public void addOcServerGroup(OcServerGroup ocServerGroup) {
        ocServerGroupMapper.insert(ocServerGroup);
    }

    @Override
    public void updateOcServerGroup(OcServerGroup ocServerGroup) {
        ocServerGroupMapper.updateByPrimaryKey(ocServerGroup);
    }

    @Override
    public void deleteOcServerGroupById(int id) {
        ocServerGroupMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<OcServerGroup> queryUerPermissionOcServerGroupByUserId(int userId){
        return ocServerGroupMapper.queryUerPermissionOcServerGroupByUserId(userId);
    }

    @Override
    public DataTable<OcServerGroup> queryUserIncludeOcServerGroupByParam(ServerGroupParam.UserServerGroupPageQuery pageQuery) {
        Page page = PageHelper.startPage(pageQuery.getPage(), pageQuery.getLength().intValue());
        List<OcServerGroup> ocServerGroupList = ocServerGroupMapper.queryUserOcServerGroupByParam(pageQuery);
        return new DataTable<>(ocServerGroupList, page.getTotal());
    }

    @Override
    public DataTable<OcServerGroup> queryUserExcludeOcServerGroupByParam(ServerGroupParam.UserServerGroupPageQuery  pageQuery) {
        Page page = PageHelper.startPage(pageQuery.getPage(), pageQuery.getLength().intValue());
        List<OcServerGroup> ocServerGroupList = ocServerGroupMapper.queryUserExcludeOcServerGroupByParam(pageQuery);
        return new DataTable<>(ocServerGroupList, page.getTotal());
    }


}
