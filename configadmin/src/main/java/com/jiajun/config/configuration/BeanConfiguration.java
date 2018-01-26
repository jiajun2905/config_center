package com.jiajun.config.configuration;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhangjiajun on 2018/1/26.
 */
@Configuration
public class BeanConfiguration {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /*@Bean
    public CuratorZookeeperClient zookeeperClient() throws Exception {
        String zookeeperAddress = "";
        int sessionTimeout = 3000;
        int connectionTimeout = 2000;

        CuratorZookeeperClient client = new CuratorZookeeperClient(zookeeperAddress, sessionTimeout, connectionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                logger.info("default watch : {}", watchedEvent.toString());
            }
        }, new RetryOneTime(1000));
        client.start();
        return client;
    }*/

    @Bean
    public CuratorFramework zookeeperClient() throws Exception {
        String zookeeperAddress = "127.0.0.1:2181";
        int sessionTimeout = 3000;
        int connectionTimeout = 2000;

        CuratorFramework client = CuratorFrameworkFactory.newClient
                (zookeeperAddress,sessionTimeout,connectionTimeout,new RetryOneTime(1000));

        client.start();
        return client;
    }
}
