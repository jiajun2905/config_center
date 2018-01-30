package com.jiajun.config.client;

import com.jiajun.config.netty.JsonCoderHandler;
import com.jiajun.config.netty.NettyMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by zhangjiajun on 2018/1/30.
 */
@Component
public class ConfigClient {

    private Channel channel;

    public void bootstrap() throws InterruptedException {
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
                                .addLast(new ClientReaderHandler());
                    }
                });

        bootstrap.bind("127.0.0.1",1226);
        ChannelFuture sync = bootstrap.connect().sync();
        if (sync.isSuccess()){
            channel = sync.channel();
        }else {
            throw new RuntimeException();
        }
    }

    public void write(NettyMessage message){
        if (channel.isWritable()){
            channel.writeAndFlush(message);
        }else {
            throw new RuntimeException();
        }
    }
}
