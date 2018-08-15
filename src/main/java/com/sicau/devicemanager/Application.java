package com.sicau.devicemanager;

import com.sicau.devicemanager.websocket.WebSocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.sicau.devicemanager.dao")
@EnableSwagger2
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}

	@Value("${websocket.port}")
	private String port;

	@Override
	public void run(String... strings) throws Exception {
		new WebSocketServer().start(Integer.valueOf(port));
	}
}
