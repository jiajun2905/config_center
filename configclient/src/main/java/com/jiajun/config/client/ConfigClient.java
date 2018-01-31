package com.jiajun.config.client;

import com.jiajun.config.netty.JsonCoderHandler;
import com.jiajun.config.netty.MessageEventEnum;
import com.jiajun.config.netty.NettyMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by zhangjiajun on 2018/1/30.
 */
@Component
public class ConfigClient {

    private Channel channel;

    @PostConstruct
    public void bootstrap() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup(3);
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new LengthFieldBasedFrameDecoder(100000,0,4,0,4))
                                .addLast(new JsonCoderHandler())
                                .addLast(new IdleStateHandler(5,5,5))
                                .addLast(new ClientReaderHandler());
                    }
                });

        ChannelFuture sync = bootstrap.connect("127.0.0.1",1226).sync();
        if (sync.isSuccess()){
            channel = sync.channel();
        }else {
            throw new RuntimeException();
        }

        NettyMessage message = new NettyMessage();
        message.setType(MessageEventEnum.CONNECT);
        message.setSrc("test");
        write(message);
    }

    public void write(NettyMessage message){
        if (channel.isWritable()){
            channel.writeAndFlush(message);
        }else {
            throw new RuntimeException();
        }
    }
}
