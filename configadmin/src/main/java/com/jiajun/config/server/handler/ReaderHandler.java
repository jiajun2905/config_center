package com.jiajun.config.server.handler;

import com.alibaba.fastjson.JSON;
import com.jiajun.config.netty.BizMessage;
import com.jiajun.config.netty.NettyMessage;
import com.jiajun.config.server.ChannelManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangjiajun on 2018/1/30.
 */
public class ReaderHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String src;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        NettyMessage message = (NettyMessage) msg;

        logger.info(JSON.toJSONString(msg));
        switch (message.getType()){
            case CONNECT:
                ChannelManager.putChannel(message.getSrc(),ctx.channel());
                this.src = message.getSrc();
                break;
            case HEARTBEAT:
                break;
            case BIZ:
                BizMessage bizMessage = (BizMessage) message;
                break;
            default:
                break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ChannelManager.removeChannel(src);
    }
}
