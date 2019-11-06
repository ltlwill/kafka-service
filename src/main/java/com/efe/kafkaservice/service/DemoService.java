package com.efe.kafkaservice.service;

import com.efe.kafkaservice.model.Demo;
import com.efe.kafkaservice.vo.PageInfoVO;

/**
 * 
 * <p>Abstract: </p> 
 * @author TianLong Liu
 * 2019年11月6日 上午9:20:22
 */
public interface DemoService {
	
	/**
	 * 
	 * <p>分页查询: </p>
	 * @param
	 * @author TianLong Liu
	 * @date 2019年11月6日 上午9:26:17
	 * @return PageInfoVO<?>
	 */
	PageInfoVO<?> findByPage(Demo demo,PageInfoVO<?> page);

}
