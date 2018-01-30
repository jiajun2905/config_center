package com.jiajun.config.client;

import com.jiajun.config.netty.JsonCoderHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Created by zhangjiajun on 2018/1/30.
 */
public class ConfigClient {

    public void bootstrap(){
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup(3);
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new JsonCoderHandler())
                                .addLast(new IdleStateHandler(5,5,5))
                                .addLast();
                    }
                });
    }
}
