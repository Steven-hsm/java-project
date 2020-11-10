package com.hsm.server;

import com.hsm.handler.DiscardServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: DiscardServer
 * @description: TODO
 * @date 2020/11/10 17:02
 */
public class DiscardServer {
    /**
     * 端口
     */
    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() throws Exception{
        //处理I/O操作的多线程事件循环器 boos用于接收进来的连接,worker用来处理已经被接收的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //启动 NIO 服务的辅助启动类
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    //事件处理,用来处理一个最近的已经接收的Channel
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(port).sync();

            // 等待服务器  socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args)  throws Exception{
        new DiscardServer(990).run();
    }
}
