package com.sicau.devicemanager.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * 接受/处理/响应客户端websocket请求的核心业务处理类
 * @author Yazhe
 * Created at 13:58 2018/8/10
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<Object> {

	private WebSocketServerHandshaker handShaker;
	private static final String WEB_SOCKET_URL = "ws://localhost:8888/websocket";
	private static ChannelGroup group = WebSocketServer.getGroup();

	//处理客户端websocket请求的和新方法
	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
		//处理客户端向服务端发起http握手请求的业务
		if (o instanceof FullHttpRequest){
			handHttpRequest(channelHandlerContext,(FullHttpRequest)o);
		}else if (o instanceof WebSocketFrame){
			//处理websocket连接业务
			handWebSocketFrame(channelHandlerContext,(WebSocketFrame)o);
		}
	}

	/**
	 * 处理客户端与服务端之间的websocket业务
	 * @param ctx
	 * @param frame
	 */
	private void handWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame){
		//判断是否是关闭websocket的指令
		if (frame instanceof CloseWebSocketFrame){
			handShaker.close(ctx.channel(),(CloseWebSocketFrame) frame.retain());
		}
		//判断是否是ping消息
		if (frame instanceof PingWebSocketFrame){
			ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
			return;
		}
		//判断是否是二进制消息，如果是二进制消息，抛出异常
		if (!(frame instanceof TextWebSocketFrame)){
			System.out.println("目前我们不支持二进制消息");
			throw new RuntimeException("【"+this.getClass().getName()+"】不支持消息");
		}
		//返回应答消息
		//获取客户端向服务端发送的消息
		String request = ((TextWebSocketFrame)frame).text();
		System.out.println("服务端收到客户端的消息====>>>" + request);
		TextWebSocketFrame textWebSocketFrame =
				new TextWebSocketFrame(new Date().toString() + ctx.channel().id() +
						" ===>>>" + request);
		//群发，服务端向每个链接上来的客户端群发消息
		group.writeAndFlush(textWebSocketFrame);
		//点对点，谁发的发给谁
		//ctx.channel().writeAndFlush(textWebSocketFrame);
	}

	/**
	 * 处理客户端向服务端发起http握手请求的业务
	 * @param ctx
	 * @param req
	 */
	private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req){
		// 如果HTTP解码失败，返回HHTP异常
		if (!req.decoderResult().isSuccess() || !("websocket".equals(req.headers().get("Upgrade")))){
			sendHttpResponse(ctx,req,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
			return;
		}
		// 构造握手响应返回
		WebSocketServerHandshakerFactory webSocketServerHandshakerFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL,null,false);
		handShaker = webSocketServerHandshakerFactory.newHandshaker(req);
		if (handShaker == null){
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		}else {
			handShaker.handshake(ctx.channel(),req);
		}
	}

	private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req,
								  DefaultFullHttpResponse res){
		if (res.status().code() != 200){
			ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
			res.content().writeBytes(buf);
			buf.release();
		}
		//服务端向客户端发送数据
		ChannelFuture future = ctx.channel().writeAndFlush(res);
		// 如果是非Keep-Alive，关闭连接
		if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200){
			future.addListener(ChannelFutureListener.CLOSE);
		}
	}

	/**
	 * 客户端与服务端创建连接的时候调用
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		group.add(ctx.channel());
		System.out.println("客户端与服务端连接开启...");
	}

	/**
	 * 断开连接的时候调用
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		group.remove(ctx.channel());
		System.out.println("客户端与服务端连接关闭...");
	}

	/**
	 * 服务端接受客户端发送过来的数据结束之后调用
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	/**
	 * 异常调用
	 * @param ctx
	 * @param cause
	 * @throws Exception
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
