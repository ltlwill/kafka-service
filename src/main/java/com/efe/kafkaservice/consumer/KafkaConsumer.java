package com.efe.kafkaservice.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.efe.kafkaservice.common.Constants;

/**
 * 
 * <p>kafka消息消费者: </p> 
 * @author Liu TianLong
 * 2019年4月24日 上午10:11:40
 */
@Component
public class KafkaConsumer {
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
	
	@KafkaListener(id="keywordCalc",topics=Constants.Topics.TOPIC_PLAIN_TEXT_OUTPUT)
//	@KafkaListener(id="keywordCalc",topics=Constants.Topics.TOPIC_PLAIN_TEXT_OUTPUT,containerFactory="batchContainerFactory")
//	public void processPalinTextMessage(ConsumerRecord<?,?> record){
	public void processPalinTextMessage(org.apache.kafka.clients.consumer.KafkaConsumer<?,?> consumer,ConsumerRecord<?,?> record){
		logger.info("KafkaListener message,key:{},value:{},partition:{},topic:{}",record.key(),record.value(),record.partition(),record.topic());
//		consumer.commitAsync(); // 手动提交控制偏移量（前提是设置：enable.auto.commit=false）
	}

}
