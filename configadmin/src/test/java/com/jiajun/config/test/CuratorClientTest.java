package com.jiajun.config.test;

import com.jiajun.config.configuration.BeanConfiguration;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by zhangjiajun on 2018/1/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BeanConfiguration.class)
public class CuratorClientTest {

    @Resource
    private CuratorFramework client;

    @Test
    public void test() throws Exception {
        System.out.println(client.getState().toString());
//        String s = client.create().forPath("/local");
        Stat stat = client.setData().forPath("/local", "hello".getBytes());

        System.out.println("--------------"+stat.toString());
        System.out.println("--------------"+client.getChildren().forPath("/"));
        System.out.println("--------------"+new String(client.getData().forPath("/local")));
    }

    @Test
    public void testWatcher() throws Exception {

        client.getData().usingWatcher(new CuratorWatcher() {
            @Override
            public void process(WatchedEvent event) throws Exception {
                System.out.println("----------- " + event.toString());
            }
        }).forPath("/local");

        client.setData().forPath("/local", "test".getBytes());
//        client.createContainers("/test");
        Thread.sleep(1000);
    }

    @Test
    public void test1() throws Exception {

        client.createContainers("/local/first");
//        client.setData().forPath("/local/first","first".getBytes());
//        client.createContainers("/test");
        System.out.println("----------- " + new String(client.getData().forPath("/local/first")));
        System.out.println("----------- " + new String(client.getData().forPath("/local")));
    }
}
