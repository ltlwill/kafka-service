package com.efe.kafkaservice.serialization;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.google.gson.Gson;

/**
 * 
 * <p>
 * 自定义JSON序列化器（使用google gson库）:
 * </p>
 * 
 * @author Liu TianLong 2019年5月7日 下午5:08:35
 */
public class JsonSerializer<T> extends JsonConfiguration<T> implements
		Serializer<T> {

	@Override
	public void close() {
		
	}

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
	}

	@Override
	public byte[] serialize(String arg0, T data) {

		return new Gson().toJson(data).getBytes(DEFAULT_CHAR_SET);
	}

}
