package com.baiyi.opscloud.core.asset.factory;

import com.baiyi.opscloud.core.asset.IAssetConvert;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 资产转换业务对象工厂
 *
 * @Author baiyi
 * @Date 2021/7/30 1:21 下午
 * @Version 1.0
 */
@Slf4j
public class AssetConvertFactory {

    private AssetConvertFactory() {
    }

    private static final Map<String, IAssetConvert> context = new ConcurrentHashMap<>();

    public static IAssetConvert getIAssetConvertByAssetType(String assetType) {
        return context.get(assetType);
    }

    public static void register(IAssetConvert bean) {
        log.info("AssetConvertFactory注册: beanName = {}", bean.getClass().getSimpleName());
        context.put(bean.getAssetType(), bean);
    }

}
