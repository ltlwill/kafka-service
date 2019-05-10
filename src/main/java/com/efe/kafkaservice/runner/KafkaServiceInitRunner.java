package com.efe.kafkaservice.runner;

import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.Serialized;
import org.apache.kafka.streams.kstream.SessionWindows;
import org.apache.kafka.streams.state.KeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.efe.kafkaservice.model.Customer;

/**
 * 
 * <p>
 * </p>
 * 
 * @author Liu TianLong 2019年4月23日 上午9:30:49
 */
@Component
public class KafkaServiceInitRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory
			.getLogger(KafkaServiceInitRunner.class);

	@Override
	public void run(String... as) throws Exception {
		logger.info("KafkaServiceRunner...");
		// initWordCountRunner();
	}

	@SuppressWarnings("unused")
	private void initWordCountRunner() {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG,
				"kafka-streams-wordcount"); // 唯一标识
		// kafka服务地址，（如果出现kafka Can't resolve
		// address的错误，则需要到kafka的server.properties文件中修改listeners=PLAINTEXT://192.168.x.x:9092即可）
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.2.6:9092");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String()
				.getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes
				.String().getClass());
		
		props.put("ThisIsMyCustomSerde", com.efe.kafkaservice.serialization.JsonSerdes.of(Customer.class));

		final StreamsBuilder builder = new StreamsBuilder();
		final String inputTopic = "streams-plaintext-input", outputTopic = "streams-wordcount-output", store = "word-counts-store";
		KStream<String, String> source = builder.stream(inputTopic);
		source.flatMapValues(
				value -> {
					logger.info("message:{}", value);
					return Arrays.asList(value.toLowerCase(Locale.getDefault())
							.split("\\W+"));
				})
//				.groupBy((k,v) -> v, Serialized.with(Serdes.String(), Serdes.String()))
//				.windowedBy(SessionWindows.with(1000))
				.groupBy((key, value) -> {
					logger.info("key : {},value : {}", key, value);
					return value;
				})
				.count(Materialized
						.<String, Long, KeyValueStore<Bytes, byte[]>> as(store))
				.toStream()
				.to(outputTopic, Produced.with(Serdes.String(), Serdes.Long())); // 输出到
																					// 另一个主题中
		final Topology topology = builder.build();
		final KafkaStreams kafkaStreams = new KafkaStreams(topology, props);

		// Java 8+, using lambda expressions
		// To catch any unexpected exceptions
		/*kafkaStreams
				.setUncaughtExceptionHandler((Thread thread, Throwable th) -> {
					logger.error("Create kafka streams error", th);
				});*/
		kafkaStreams.setUncaughtExceptionHandler((thread, th) -> {
			logger.error("Create kafka streams error", th);
		});
		// attach shutdown handler to catch control-c
		Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
		// start kafka streams
		kafkaStreams.start();
	}

}
