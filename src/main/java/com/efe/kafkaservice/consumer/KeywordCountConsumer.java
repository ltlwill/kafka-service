package com.efe.kafkaservice.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.efe.kafkaservice.common.Constants;
import com.efe.kafkaservice.vo.KeywordCountVO;

/**
 * 
 * <p>关键词统计结果消费: </p> 
 * @author Liu TianLong
 * 2019年5月21日 上午9:58:27
 */
@Component
public class KeywordCountConsumer extends KafkaBusinessListener{
	
	@KafkaListener(id="category-keyword-count",topics=Constants.Topics.TOPIC_KEYWORD_COUNT_OUTPUT)
//	@KafkaListener(id="keywordCalc",topics=Constants.Topics.TOPIC_PLAIN_TEXT_OUTPUT,containerFactory="batchContainerFactory")
//	public void processPalinTextMessage(ConsumerRecord<?,?> record){
	public void keywordCountConsumer(KafkaConsumer<String,Long> consumer,ConsumerRecord<String,Long> record){
		logger.info("keyword count Listener message,key:{},value:{},partition:{},topic:{}",record.key(),record.value(),record.partition(),record.topic());
		KeywordCountVO countVo = generateKeywordVO(record.key(),record.value()); 
		logger.info("keyword count result is:{}",countVo); 
//		consumer.commitAsync(); // 手动提交控制偏移量（前提是设置：enable.auto.commit=false）
	}
	
	private KeywordCountVO generateKeywordVO(String key,Long value){
		String[] arr = key.split(Constants.SPLIT_STR);
		return new KeywordCountVO(arr[0],arr[1],value);
	}

}
