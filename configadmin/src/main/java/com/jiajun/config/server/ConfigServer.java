package com.jiajun.config.server;

import com.jiajun.config.netty.JsonCoderHandler;
import com.jiajun.config.server.handler.ReaderHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by SUMMERS on 2018/1/27.
 */
@Component
public class ConfigServer {

    @EventListener(ContextRefreshedEvent.class)
    public void bootstrap() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup(5);

        bootstrap.group(boss,worker).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast(new LengthFieldBasedFrameDecoder(100000,0,4))
                                .addLast(new JsonCoderHandler())
                                .addLast(new ReaderHandler());
                    }
                });

        bootstrap.bind(1226).sync();

    }
}
