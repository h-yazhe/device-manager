package com.sicau.devicemanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.sicau.devicemanager.dao")
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}

	@Value("${websocket.port}")
	private String port;

	@Override
	public void run(String... strings) throws Exception {
		//new WebSocketServer().start(Integer.valueOf(port));
	}
}
