package com.efe.kafkaservice.streams;

import java.util.Arrays;
import java.util.List;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.efe.kafkaservice.common.Constants;
import com.efe.kafkaservice.vo.KeywordCountVO;
import com.github.pagehelper.Constant;

/**
 * 
 * <p>
 * 关键词统计:
 * </p>
 * 
 * @author Liu TianLong 2019年5月20日 下午5:02:39
 */
@Configuration
public class KeywordCountStream {
	
	private static final Logger logger = LoggerFactory.getLogger(KeywordCountStream.class);

	@Bean
	public KStream<String, String> keywordCountkStream(StreamsBuilder streamsBuilder) {
		// 关键词统计流处理
		KStream<String, String> stream = streamsBuilder.stream(
				Constants.Topics.TOPIC_KEYWORD_COUNT_INPUT,
				Consumed.with(Serdes.String(), Serdes.String()));
		stream.flatMapValues((key,value) -> {
			logger.info("flatMapValues key:{},value:{}", key,value);
			return Arrays.asList(value.split("\\W+"));
		}).groupBy((key,value) -> {
			logger.info("groupBy key:{},value:{}", key,value);
//			return value;
			return key + Constants.SPLIT_STR + value;
		})
		.count(Materialized.<String, Long, KeyValueStore<Bytes,byte[]>> as(Constants.Stores.STORE_KEYWORD_COUNTS))
//		.count()
		.toStream()
//		.foreach((key,value) -> logger.info("foreach key:{},value:{}",key,value));
//		.print(Printed.<String,Long>toFile("D:\\test\\kafka-out\\keyword-count-out-file").withLabel("keyword-Stream-Sysout")); 
//		.print(Printed.<String,Long>toSysOut().withLabel("keyword-Stream-Sysout"));
		.to(Constants.Topics.TOPIC_KEYWORD_COUNT_OUTPUT,
						Produced.with(Serdes.String(), Serdes.Long())); // 输出到另一个主题中
		return stream;
	}

	@Bean
	public NewTopic inputTopic() {
		return new NewTopic(Constants.Topics.TOPIC_KEYWORD_COUNT_INPUT, 1,
				(short) 1);
	}

	@Bean
	public NewTopic outputTopic() {
		return new NewTopic(Constants.Topics.TOPIC_KEYWORD_COUNT_OUTPUT, 1,
				(short) 1);
	}

}
