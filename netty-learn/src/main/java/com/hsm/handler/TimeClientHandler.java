package com.hsm.handler;

import com.hsm.entity.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: TimeClientHandler
 * @description: TODO
 * @date 2020/11/10 17:30
 */
public class TimeClientHandler  extends SimpleChannelInboundHandler {
    private ByteBuf buf;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        buf = ctx.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        buf.release();
        buf = null;
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //=============================1.直接接收流数据===========================
        /*ByteBuf m = (ByteBuf) msg;
        try {
            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        } finally {
            m.release();
        }*/
        //=============================1.直接接收流数据===========================

        //=============================2.构造内部的可积累的缓冲===========================
        /*ByteBuf m = (ByteBuf) msg;
        buf.writeBytes(m);
        m.release();

        if (buf.readableBytes() >= 4) {
            long currentTimeMillis = (buf.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        }*/
        //=============================2.构造内部的可积累的缓冲===========================

        //=============================3.可以使用单独的处理器处理===========================
        /*ByteBuf m = (ByteBuf) msg;
        try {
            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        } finally {
            m.release();
        }*/
        //=============================3.可以使用单独的处理器处理===========================

        //=============================4.使用实体对象接收===========================
        UnixTime m = (UnixTime) msg;
        System.out.println(m);
        ctx.close();
        //=============================4.使用实体对象接收===========================
    }
}
