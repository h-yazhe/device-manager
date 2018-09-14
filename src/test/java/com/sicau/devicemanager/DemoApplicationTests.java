package com.sicau.devicemanager;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private StringEncryptor stringEncryptor;

	@Test
	public void contextLoads() {
	}

	@Test
	public void encrypt(){
		System.out.println(stringEncryptor.encrypt("123456"));
	}

}
