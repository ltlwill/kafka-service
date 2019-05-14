package com.efe.kafkaservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * <p>kafka业务服务应用入口: </p> 
 * @author Liu TianLong
 * 2019年4月23日 上午9:05:48
 */
@EnableTransactionManagement
@MapperScan("com.efe.kafkaservice.**.dao")
@SpringBootApplication
public class KafkaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaServiceApplication.class, args);
	}

}
