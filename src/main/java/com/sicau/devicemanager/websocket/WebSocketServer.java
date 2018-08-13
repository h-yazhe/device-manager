package com.sicau.devicemanager.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * websocker服务端启动类
 * @author Yazhe
 * Created at 17:38 2018/8/10
 */
public class WebSocketServer {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

	/**
	 * 存储每一个客户端介入进来时的channel对象
	 */
	private static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	public static ChannelGroup getGroup() {
		return group;
	}

	/**
	 * 通过此方法启动
	 * @param port
	 */
	public void start(int port){
		// Boss线程：由这个线程池提供的线程是boss种类的，用于创建、连接、绑定socket， （有点像门卫）然后把这些socket传给worker线程池。
		// 在服务器端每个监听的socket都有一个boss线程来处理。在客户端，只有一个boss线程来处理所有的socket。
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// Worker线程：Worker线程执行所有的异步I/O，即处理操作
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try{
			// ServerBootstrap 启动NIO服务的辅助启动类,负责初始话netty服务器，并且开始监听端口的socket请求
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup,workGroup)
					// 设置非阻塞,用它来建立新accept的连接,用于构造serversocketchannel的工厂类
					.channel(NioServerSocketChannel.class)
					// ChildChannelHandler 对出入的数据进行的业务操作,其继承ChannelInitializer
					.childHandler(new ChannelInitializer<SocketChannel>(){
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							// 设置30秒没有读到数据，则触发一个READER_IDLE事件。
							// pipeline.addLast(new IdleStateHandler(30, 0, 0));
							// HttpServerCodec：将请求和应答消息解码为HTTP消息
							ch.pipeline().addLast("http-codec",new HttpServerCodec());
							// HttpObjectAggregator：将HTTP消息的多个部分合成一条完整的HTTP消息
							ch.pipeline().addLast("aggregator",new HttpObjectAggregator(65536));
							// ChunkedWriteHandler：向客户端发送HTML5文件
							ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
							//设置websocket的url路径，设置后连接可以建立，但是发送消息时就会断开连接
							//ch.pipeline().addLast(new WebSocketServerProtocolHandler("/aaa"));
							// 在管道中添加我们自己的接收数据实现方法
							ch.pipeline().addLast("handler",new WebSocketHandler());
						}
					});
			//开启连接
			Channel channel = bootstrap.bind(port).sync().channel();
			logger.info("websocket server started on port: " + port);
			//等待连接关闭
			channel.closeFuture().sync();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//优雅的退出程序
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
}
