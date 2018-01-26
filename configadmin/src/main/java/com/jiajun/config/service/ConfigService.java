package com.jiajun.config.service;

import com.jiajun.config.entity.MetaConfig;

import java.util.List;

/**
 * Created by zhangjiajun on 2018/1/26.
 */
public interface ConfigService {

    List<MetaConfig> getConfigList(String environment);
}
