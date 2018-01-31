package com.jiajun.config.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.nio.charset.Charset;

/**
 * Created by SUMMERS on 2018/1/31.
 */
public class LengthFieldFrameOutboundHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        ctx.write(buf.readableBytes(),promise);
        String s = buf.toString(Charset.forName("utf-8"));
        ctx.writeAndFlush(msg,promise);
    }
}
