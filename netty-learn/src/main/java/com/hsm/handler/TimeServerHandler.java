package com.hsm.handler;

import com.hsm.entity.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: TimeServerHandler
 * @description: TODO
 * @date 2020/11/10 17:18
 */
public class TimeServerHandler extends SimpleChannelInboundHandler {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    /**
     * 连接被建立时调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       /* final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        //一个channelFuture代表一个还没有发生的I/O操作,这意味着任何一个请求操作都不会立马执行
        final ChannelFuture f = ctx.writeAndFlush(time);
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        });*/
        ChannelFuture f = ctx.writeAndFlush(new UnixTime());
        f.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
