package com.jiajun.config.server;

import com.jiajun.config.netty.BizMessage;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhangjiajun on 2018/1/30.
 */
public class ChannelManager {

    private static Map<String,Channel> channelMap = new ConcurrentHashMap<>();

    public static void putChannel(String key,Channel channel){
        channelMap.put(key,channel);
    }

    public static void removeChannel(String key){
        channelMap.remove(key);
    }

    public static Channel getChannel(String key){
        return channelMap.get(key);
    }

    public static void write(BizMessage bizMessage){
        Channel channel = channelMap.get(bizMessage.getSrc());
        if (channel == null){
            return;
        }
        if (!channel.isWritable()){
            channelMap.remove(bizMessage.getSrc());
            return;
        }
        channel.writeAndFlush(bizMessage);
    }
}
