package com.hsm.netty.chapter.four;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: TimeEncoder
 * @description: TODO
 * @date 2020/11/10 17:54
 */
public class TimeEncoder extends MessageToByteEncoder<UnixTime> {
    @Override
    protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) throws Exception {
        out.writeInt((int)msg.value());
    }

}
