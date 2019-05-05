package com.efe.kafkaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * <p>kafka业务服务应用入口: </p> 
 * @author Liu TianLong
 * 2019年4月23日 上午9:05:48
 */
@SpringBootApplication
public class KafkaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaServiceApplication.class, args);
	}

}
