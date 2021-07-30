package com.baiyi.opscloud.domain.vo.server;

import com.baiyi.opscloud.domain.types.BusinessTypeEnum;
import com.baiyi.opscloud.domain.vo.base.BaseVO;
import com.baiyi.opscloud.domain.vo.env.EnvVO;
import com.baiyi.opscloud.domain.vo.tag.TagVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author baiyi
 * @Date 2020/2/21 4:56 下午
 * @Version 1.0
 */
public class ServerVO {

    public interface IServer {

        void setServer(Server server);

        Integer getServerId();
    }


    @EqualsAndHashCode(callSuper = true)
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel
    public static class Server extends BaseVO implements EnvVO.IEnv, TagVO.ITags, ServerGroupVO.IServerGroup, ServerAccountVO.IAccount, Serializable {

        private static final long serialVersionUID = -1011261913967456450L;
        private List<TagVO.Tag> tags;

        private EnvVO.Env env;

        private ServerGroupVO.ServerGroup serverGroup;

        private final int businessType = BusinessTypeEnum.SERVER.getType();

        private List<ServerAccountVO.Account> accounts;

        // 需要绑定关系的资产id
        private Integer datasourceInstanceAssetId;

        @Override
        public Integer getServerId() {
            return id;
        }

        @Override
        public int getBusinessId() {
            return id;
        }

        @ApiModelProperty(value = "主键", example = "1")
        private Integer id;

        @ApiModelProperty(value = "服务器名称")
        @NotNull(message = "服务器名称不能为空")
        private String name;

        @ApiModelProperty(value = "显示名称")
        private String displayName;

        @ApiModelProperty(value = "服务器组id", example = "1")
        @NotNull(message = "服务器组不能为空")
        private Integer serverGroupId;

        @ApiModelProperty(value = "环境类型", example = "1")
        @NotNull(message = "环境类型不能为空")
        private Integer envType;

        @ApiModelProperty(value = "公网ip")
        private String publicIp;

        @ApiModelProperty(value = "私网ip")
        @NotNull(message = "私网ip不能为空")
        private String privateIp;

        @ApiModelProperty(value = "服务器类型", example = "1")
        @NotNull(message = "服务器类型不能为空")
        private Integer serverType;

        @ApiModelProperty(value = "地区")
        private String area;

        @ApiModelProperty(value = "系统类型")
        private String osType;

        @ApiModelProperty(value = "序号", example = "1")
        private Integer serialNumber;

        @ApiModelProperty(value = "监控状态", example = "1")
        private Integer monitorStatus;

        @ApiModelProperty(value = "资源描述")
        private String comment;

        @ApiModelProperty(value = "服务器状态", example = "1")
        private Integer serverStatus;

        @ApiModelProperty(value = "有效")
        private Boolean isActive;

        @ApiModelProperty(value = "资产id")
        private Integer assetId;
    }

    @Data
    @NoArgsConstructor
    @ApiModel
    public static class DeployVersion {

        @ApiModelProperty(value = "版本名称")
        private String versionName;

        private Integer buildId;

        private Integer jobId;

        private String privateIp;

        @ApiModelProperty(value = "创建时间")
        @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
        private Date createTime;

    }

}
