package com.jiajun.config.netty;

import com.jiajun.config.entity.MetaConfig;

import java.util.List;

/**
 * Created by SUMMERS on 2018/1/27.
 */
public class BizMessageBody extends MessageBody {

    private String rootPath;

    private List<MetaConfig> configs;

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public List<MetaConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<MetaConfig> configs) {
        this.configs = configs;
    }
}
