package com.thebudding.book.rxjava;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HttpTcpNettyServer {

  public static void main(String[] args) throws Exception {
    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    try {
      new ServerBootstrap()
          .option(ChannelOption.SO_BACKLOG, 50_000)
          .group(bossGroup, workerGroup)
          .channel(NioServerSocketChannel.class)
          .childHandler(new HttpInitializer())
          .bind(8080)
          .sync()
          .channel()
          .closeFuture()
          .sync();
    } finally{
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }
}
