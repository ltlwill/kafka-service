package com.efe.kafkaservice.serialization;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.google.gson.Gson;

/**
 * 
 * <p>
 * 自定义JSON反序列化器（使用google gson库）:
 * </p>
 * 
 * @author Liu TianLong 2019年5月7日 下午5:16:32
 */
public class JsonDeserializer<T> extends JsonConfiguration<T> implements
		Deserializer<T> {

	@Override
	public void close() {

	}

	@Override
	public void configure(Map<String, ?> map, boolean flag) {

	}

	@Override
	public T deserialize(String s, byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		return new Gson().fromJson(new String(bytes, DEFAULT_CHAR_SET),
				getGenericClass());
	}

}
