package com.efe.kafkaservice.serialization;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

/**
 * 
 * <p>
 * Serde管理:
 * </p>
 * 
 * @author Liu TianLong 2019年5月7日 下午6:05:45
 */
public final class JsonSerdes {

	public static <T> Serde<T> of(Class<T> t) {
		return new WrapperSerde<T>(new JsonSerializer<T>(),
				new JsonDeserializer<T>());
	}

	public static final class WrapperSerde<T> implements Serde<T> {

		private Serializer<T> serializer;
		private Deserializer<T> deserializer;

		public WrapperSerde(Serializer<T> serializer,
				Deserializer<T> deserializer) {
			this.serializer = serializer;
			this.deserializer = deserializer;
		}

		@Override
		public void close() {

		}

		@Override
		public void configure(Map<String, ?> map, boolean flag) {
			this.serializer.configure(map, flag);
			this.deserializer.configure(map, flag);
		}

		@Override
		public Deserializer<T> deserializer() {
			return this.deserializer;
		}

		@Override
		public Serializer<T> serializer() {
			return this.serializer;
		}

	}

}
