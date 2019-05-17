package com.efe.kafkaservice.streams;

import java.util.Arrays;
import java.util.Locale;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import com.efe.kafkaservice.common.Constants;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class WordCountStream {
	private final Logger logger = LoggerFactory
			.getLogger(WordCountStream.class);

	/**
	 * 
	 * <p>kafka streams进行单词统计: </p>
	 * @param
	 * @author Liu TianLong
	 * @date 2019年5月7日 下午2:50:04
	 * @return KStream<Integer,String>
	 */
	@Bean
	public KStream<Integer, String> kStream(StreamsBuilder streamsBuilder) {
		KStream<Integer, String> stream = streamsBuilder
				.stream(Constants.Topics.TOPIC_PLAIN_TEXT_INPUT);
		// stream.map((k, v) -> new KeyValue(k, v.toUpperCase())).to("ks1Out",
		// Produced.with(Serdes.Integer(), new JsonSerde()));
		stream.flatMapValues(
				value -> {
					logger.info("message:{}", value);
					return Arrays.asList(value.toLowerCase(Locale.getDefault())
							.split("\\W+"));
				})
				.groupBy((key, value) -> {
					logger.info("key : {},value : {}", key, value);
					return value;
				})
				.count(Materialized
						.<String, Long, KeyValueStore<Bytes, byte[]>> as(Constants.Stores.STORE_WORD_COUNTS))
				.toStream()
				.to(Constants.Topics.TOPIC_PLAIN_TEXT_OUTPUT,
						Produced.with(Serdes.String(), Serdes.Long())); // 输出到另一个主题中
		return stream;
	}
	
	@Bean
	public NewTopic plaintextInputTopic(){
		return new NewTopic(Constants.Topics.TOPIC_PLAIN_TEXT_INPUT, 1, (short)1);
	}
	
	@Bean
	public NewTopic plaintextOutputTopic(){
		return new NewTopic(Constants.Topics.TOPIC_PLAIN_TEXT_OUTPUT, 1, (short)1);
	}
	
	/*
	@Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>>
                        kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory =
                                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }
	
	@Bean
    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }
*/
    

}
