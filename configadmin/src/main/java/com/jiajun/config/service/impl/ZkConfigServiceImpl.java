package com.jiajun.config.service.impl;

import com.jiajun.config.entity.MetaConfig;
import com.jiajun.config.service.ConfigService;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangjiajun on 2018/1/26.
 */
@Service
public class ZkConfigServiceImpl implements ConfigService {

    @Resource
    private CuratorFramework client;


    @Override
    public List<MetaConfig> getConfigList(String environment) {



        return null;
    }
}
