package com.jiajun.config.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.util.StringUtils;

/**
 * Created by SUMMERS on 2018/1/27.
 */
public class JsonDecoderHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = (String) msg;
//        if (StringUtils.is)

    }
}
