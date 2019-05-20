package com.efe.kafkaservice.streams;

import java.util.Arrays;
import java.util.List;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.efe.kafkaservice.common.Constants;

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

	@Bean
	public KStream<String, String> keywordCountkStream(StreamsBuilder streamsBuilder) {
		// 关键词统计流处理
		KStream<String, String> stream = streamsBuilder.stream(
				Constants.Topics.TOPIC_KEYWORD_COUNT_INPUT,
				Consumed.with(Serdes.String(), Serdes.String()));
		stream.flatMapValues((key,value) -> {
			List<String> words = Arrays.asList(value.split("\\W+"));
			return words;
		});
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
