package com.hsm.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: DiscardServerHandler
 * @description: TODO
 * @date 2020/11/10 16:53
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //=================================1.直接将消息丢弃==============================
        /*super.channelRead(ctx, msg);
        //默默丢弃收到的数据
        //ByteBuf 是一个引用计数对象，这个对象必须显示地调用 release() 方法来释放
        ((ByteBuf) msg).release();*/
        //=================================1.直接将消息丢弃==============================

        //=================================2.输出控制台之后再丢弃==============================
        /*ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) { // (1)
                System.out.print((char) in.readByte());
                System.out.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }*/
        //=================================2.输出控制台之后再丢弃==============================

        //=================================3.对消息做一个简单的响应==============================
        ctx.write(msg);
        ctx.flush();
        //=================================3.对消息做一个简单的响应==============================
    }
}
