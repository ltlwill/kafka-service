package com.efe.kafkaservice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

/**
 * 
 * <p>kafka自定义配置: </p> 
 * @author Liu TianLong
 * 2019年4月24日 下午2:54:38
 */
@Configuration
public class KafkaConfig {

	/**
	 * 
	 * <p>批量消费工厂（在使用时指定containerFactory属性为：batchContainerFactory 即可）: </p>
	 * @param
	 * @author Liu TianLong
	 * @date 2019年4月24日 下午2:55:10
	 * @return ConcurrentKafkaListenerContainerFactory<?,?>
	 */
    @Bean("batchContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<?,?> listenerContainer() {
        ConcurrentKafkaListenerContainerFactory<?,?> container = new ConcurrentKafkaListenerContainerFactory<>();
        container.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerProps()));
        //设置并发量，小于或等于Topic的分区数
        container.setConcurrency(5);
        //设置为批量监听
        container.setBatchListener(true);
        return container;
    }
    
    private Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.2.6:9092");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "5");  //一次拉取消息数量,这个并发量根据分区数决定，必须小于等于分区数，否则会有线程一直处于空闲状态
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put("spring.deserializer.key.delegate.class", StringDeserializer.class);
        props.put("spring.deserializer.value.delegate.class", LongDeserializer.class);
        props.put("default.key.serde", Serdes.String().getClass());
        props.put("default.value.serde", Serdes.String().getClass());
        return props;
    }
    
    
   /* @Bean("defaultProducerFactory")
    public ProducerFactory<?, ?> producerFactory(){
    	 return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
    
    private Map<String, Object> producerConfigs() {
    	Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,LongDeserializer.class);
        return props;
    }*/
}
