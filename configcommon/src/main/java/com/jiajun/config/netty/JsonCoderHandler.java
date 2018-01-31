package com.jiajun.config.netty;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by SUMMERS on 2018/1/27.
 */
public class JsonCoderHandler extends ByteToMessageCodec<NettyMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, ByteBuf out) throws Exception {


        String json = JSON.toJSONString(msg);
        int value = json.getBytes("utf-8").length + 4;
        out.writeInt(value);
        out.writeInt(msg.getType().getCode());

        out.writeBytes(json.getBytes("utf-8"));

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4){
            ReferenceCountUtil.release(in);
            return;
        }
        int i = in.readInt();
        MessageEventEnum messageEventEnum = MessageEventEnum.valueOfCode(i);
        if (messageEventEnum == null){
            ReferenceCountUtil.release(in);
            return;
        }

        String json = in.toString(Charset.forName("utf-8"));
        Object o = JSON.parseObject(json, messageEventEnum.getMessageClass());
        out.add(o);
        in.clear();// 待考究，out待考究
//        ReferenceCountUtil.release(in);
    }
}
