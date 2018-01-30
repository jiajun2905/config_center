package com.jiajun.config.client;

import com.alibaba.fastjson.JSON;
import com.jiajun.config.netty.NettyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by SUMMERS on 2018/1/30.
 */
public class ClientReaderHandler extends SimpleChannelInboundHandler<NettyMessage> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyMessage msg) throws Exception {
        logger.info(JSON.toJSONString(msg));

        switch (msg.getType()){
            case CONNECT:
                logger.info("connect success");
                break;
            case BIZ:
                break;
            default:
                break;
        }
    }
}
