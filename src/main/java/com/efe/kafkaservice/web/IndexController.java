package com.efe.kafkaservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.efe.kafkaservice.common.Constants;

/**
 * 
 * <p>: </p> 
 * @author Liu TianLong
 * 2019年4月23日 上午10:09:11
 */
@RestController
public class IndexController extends BaseController{
	
	@Autowired
	private KafkaTemplate<String,String>  kafkaTemplate;

	@GetMapping
	public String index(){
		logger.trace("Hello kafka service--trace");
		logger.debug("Hello kafka service--debug");
		logger.info("Hello kafka service--info");
		logger.warn("Hello kafka service--warn");
		logger.error("Hello kafka service--error");
		return "Hello kafka service.";
	}
	
	@GetMapping("/message/{content}")
	public String sendMessage(@PathVariable String content){
		logger.info("request message:{}",content); 
		kafkaTemplate.send(Constants.Topics.TOPIC_PLAIN_TEXT_INPUT, content);
//		kafkaTemplate.send(topic, key, data)
		return "success";
	}
}
