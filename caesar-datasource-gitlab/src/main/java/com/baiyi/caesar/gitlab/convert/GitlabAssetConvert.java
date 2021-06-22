package com.baiyi.caesar.gitlab.convert;

import com.baiyi.caesar.common.type.DsAssetTypeEnum;
import com.baiyi.caesar.datasource.builder.AssetContainer;
import com.baiyi.caesar.datasource.builder.AssetContainerBuilder;
import com.baiyi.caesar.domain.generator.caesar.DatasourceInstance;
import com.baiyi.caesar.domain.generator.caesar.DatasourceInstanceAsset;
import org.gitlab.api.models.GitlabGroup;
import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabUser;

/**
 * @Author baiyi
 * @Date 2021/6/21 5:05 下午
 * @Version 1.0
 */
public class GitlabAssetConvert {


    public static AssetContainer toAssetContainer(DatasourceInstance dsInstance, GitlabUser entry) {
        DatasourceInstanceAsset asset = DatasourceInstanceAsset.builder()
                .instanceUuid(dsInstance.getUuid())
                .assetId(String.valueOf(entry.getId()))
                .name(entry.getName())
                .assetKey(entry.getUsername())
                .assetKey2(entry.getEmail())
                .isActive(entry.isBlocked() == null || !entry.isBlocked())
                .createdTime(entry.getCreatedAt())
                .assetType(DsAssetTypeEnum.USER.name())
                .kind("gitlabUser")
                .build();

        return AssetContainerBuilder.newBuilder()
                .paramAsset(asset)
                .paramProperty("isAdmin", entry.isAdmin())
                .paramProperty("projectsLimit", entry.getProjectsLimit())
                .build();
    }

    public static AssetContainer toAssetContainer(DatasourceInstance dsInstance, GitlabProject entry) {
        DatasourceInstanceAsset asset = DatasourceInstanceAsset.builder()
                .instanceUuid(dsInstance.getUuid())
                .assetId(String.valueOf(entry.getId()))
                .name(entry.getName())
                .assetKey(entry.getSshUrl())
                .assetKey2(entry.getWebUrl())
                .createdTime(entry.getCreatedAt())
                .description(entry.getDescription())
                .assetType(DsAssetTypeEnum.GITLAB_PROJECT.name())
                .kind("gitlabProject")
                .build();

        return AssetContainerBuilder.newBuilder()
                .paramAsset(asset)
                .paramProperty("visibility", entry.getVisibility())
                .paramProperty("nameWithNamespace", entry.getNameWithNamespace())
                .paramProperty("httUrl", entry.getHttpUrl())
                .paramProperty("namespaceId", entry.getNamespace().getId())
                .paramProperty("namespaceName", entry.getNamespace().getName())
                .paramProperty("namespacePath", entry.getNamespace().getPath())
                .paramProperty("namespaceKind", entry.getNamespace().getKind())
                .paramProperty("defaultBranch", entry.getDefaultBranch())
                .build();
    }

    public static AssetContainer toAssetContainer(DatasourceInstance dsInstance, GitlabGroup entry) {
        DatasourceInstanceAsset asset = DatasourceInstanceAsset.builder()
                .instanceUuid(dsInstance.getUuid())
                .assetId(String.valueOf(entry.getId()))
                .name(entry.getName())
                .assetKey(entry.getWebUrl())
                .assetKey2(entry.getPath())
                .description(entry.getDescription())
                .assetType(DsAssetTypeEnum.GITLAB_GROUP.name())
                .kind("gitlabProject")
                .build();

        return AssetContainerBuilder.newBuilder()
                .paramAsset(asset)
                .paramProperty("visibility",entry.getVisibility())
                .paramProperty("parentId", entry.getParentId())
                .build();
    }
}
