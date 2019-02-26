package com.rabbitmq.provider;

import com.rabbitmq.provider.send.RabbitMqSendSevice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProviderApplicationTests {

	@Autowired
	private RabbitMqSendSevice rabbitMqSendSevice;

	@Test
	public void sendTest() {
		for (int i = 0;i < 1; i++) {
			rabbitMqSendSevice.sendTopic(i);
		}

	}

}
