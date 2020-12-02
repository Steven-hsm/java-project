package com.hsm.decoder;

import com.hsm.entity.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: TimeDecoder
 * @description: TODO
 * @date 2020/11/10 17:42
 */
public class TimeDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //===================================1.将4个以上字节分为一组=======================
        /*if (in.readableBytes() < 4) {
            return;
        }
        out.add(in.readBytes(4));*/
        //===================================1.将4个以上字节分为一组=======================

        //===================================1.封装成对象返回=======================
        if (in.readableBytes() < 4) {
            return;
        }
        out.add(new UnixTime(in.readUnsignedInt()));
        //===================================1.封装成对象返回=======================
    }
}
