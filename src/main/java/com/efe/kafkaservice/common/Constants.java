package com.efe.kafkaservice.common;

/**
 * 
 * <p>常量类: </p> 
 * @author Liu TianLong
 * 2019年4月24日 上午10:05:47
 */
public final class Constants {
	
	public static final class Topics{
		public static final String TOPIC_PLAIN_TEXT_INPUT = "streams-plaintext-input";
		public static final String TOPIC_PLAIN_TEXT_OUTPUT = "streams-wordcount-output";
	}
	
	public static final class Stores{
		public static final String STORE_WORD_COUNTS = "word-counts-store";
	}

}
