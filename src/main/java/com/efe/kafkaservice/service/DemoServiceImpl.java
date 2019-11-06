package com.efe.kafkaservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.efe.kafkaservice.dao.DemoDao;
import com.efe.kafkaservice.model.Demo;
import com.efe.kafkaservice.vo.PageInfoVO;

/**
 * 
 * <p>Abstract: </p> 
 * @author TianLong Liu
 * 2019年11月6日 上午9:20:45
 */
public class DemoServiceImpl implements DemoService {
	
	@Autowired
	private DemoDao demoDao;

	@Override
	public PageInfoVO<?> findByPage(Demo demo,PageInfoVO<?> page) {
		// 分页查询
		List<Demo> rows =  demoDao.getListPage(demo, page.toRowBounds());
		// 返回分页信息
		return page.with(rows);
	}

}
