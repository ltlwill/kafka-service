package com.efe.kafkaservice;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.functions.api.Context;
import org.slf4j.Logger;

/**
 * <p>apache pulsar demo: </p> 
 * @author Liu TianLong
 * 2019年5月22日 上午11:10:26
 */
public class PulsarDemo {
	
	public void test1() throws Exception{
		PulsarClient client = PulsarClient.builder().serviceUrl("pulsar://localhost:6650").build();
		client.newProducer().createAsync();
	}
	
	public void test2() throws Exception{
		
	}
	
	/**
	 * java native 方式
	 * <p>Abstract: </p> 
	 * @author Liu TianLong
	 * 2019年5月22日 上午11:08:37
	 */
	public static class NativeCalcWordCount implements java.util.function.Function<String, String>{

		@Override
		public String apply(String input) { 
			return String.format("java native function %s!", input);
		}
		
	}
	
	/**
	 * pulsar functions api
	 * <p>Abstract: </p> 
	 * @author Liu TianLong
	 * 2019年5月22日 上午11:10:01
	 */
	public static class PulsarCalcWordCount implements org.apache.pulsar.functions.api.Function<String, String>{

		@Override
		public String process(String input, Context context) throws Exception {
			Logger logger = context.getLogger();
			logger.info("current tenant is {}",context.getTenant());
			return String.format("pulsar function %s!", input);
		}
		
	}

}
