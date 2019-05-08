package com.efe.kafkaservice.serialization;

import java.lang.reflect.ParameterizedType;
import java.nio.charset.Charset;

import com.google.gson.Gson;

/**
 * 
 * <p>
 * JSON序列化/反序列化 配置相关
 * </p>
 * 
 * @author Liu TianLong 2019年5月7日 下午5:11:32
 */
public class JsonConfiguration<T> {

	public static final String DEFAULT_CHAR_SET_STR = "UTF-8";

	public static final Charset DEFAULT_CHAR_SET = Charset
			.forName(DEFAULT_CHAR_SET_STR);

	protected Gson gson = new Gson();

	/**
	 * 
	 * <p>
	 * 动态获取泛型的具体类型的class:
	 * </p>
	 * 
	 * @param
	 * @author Liu TianLong
	 * @date 2019年5月7日 下午5:27:08
	 * @return Class<T>
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getGenericClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

}
