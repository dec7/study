package com.thebudding.book.rxjava;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpInitializer extends ChannelInitializer<SocketChannel> {

  private final HttpHandler httpHandler = new HttpHandler();

  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ch
        .pipeline()
        .addLast(new HttpServerCodec())
        .addLast(httpHandler);

  }
}
