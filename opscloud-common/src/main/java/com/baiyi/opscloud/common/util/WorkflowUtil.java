package com.baiyi.opscloud.common.util;

import com.baiyi.opscloud.domain.vo.workorder.WorkflowVO;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

/**
 * @Author baiyi
 * @Date 2022/1/11 2:42 PM
 * @Version 1.0
 */
public class WorkflowUtil {

    private WorkflowUtil() {
    }

    public static WorkflowVO.Workflow toWorkflowView(String workflow) {
        if (StringUtils.isEmpty(workflow))
            return WorkflowVO.Workflow.EMPTY;
        try {
            Yaml yaml = new Yaml();
            Object result = yaml.load(workflow);
            return new GsonBuilder().create().fromJson(JSONUtil.writeValueAsString(result), WorkflowVO.Workflow.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return WorkflowVO.Workflow.EMPTY;
        }
    }

}
