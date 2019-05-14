package com.efe.kafkaservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efe.kafkaservice.dao.DemoDao;
import com.efe.kafkaservice.model.Demo;
import com.efe.kafkaservice.vo.PageInfoVO;

/**
 * 
 * <p>
 * demo:
 * </p>
 * 
 * @author Liu TianLong 2019年5月13日 上午10:34:24
 */
@RestController
@RequestMapping("/demo")
public class DemoController extends BaseController {

	@Autowired
	private DemoDao demoDao;

	@PostMapping("/{name}")
	@Transactional
	public String addName(@PathVariable String name) {
		logger.info("add name:{}", name);
		demoDao.add(new Demo(name));
		return "success";
	}

	@GetMapping("/list")
	public PageInfoVO<?> list(PageInfoVO<?> page,
			@RequestParam(required = false) String name) {
		logger.info("query name:{}", name);
		Demo demo = new Demo(name);
		// return demoDao.getListByEntity(new Demo(name));

		// return PageHelper.startPage(page.getPageNum(), page.getPageSize())
		// .doSelectPage(() -> demoDao.getListByEntity(new Demo(name)));

		// List<Demo> demos = demoDao.getListPage(demo,
		// PageUtil.toRowBounds(page));
		// long total = demoDao.count(demo);
		// return PageInfoVo.with(page, demos, total);
		// 分页获取
		// return new PageInfo<Demo>(demoDao.getListPage(demo,
		// PageUtil.toRowBounds(page)));
		return page.with(demoDao.getListPage(demo, page.toRowBounds()));

	}

}
