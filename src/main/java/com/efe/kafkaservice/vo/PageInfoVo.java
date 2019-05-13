package com.efe.kafkaservice.vo;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;

import com.github.pagehelper.PageInfo;

/**
 * 
 * <p>分页业务VO: </p> 
 * @author Liu TianLong
 * 2019年5月13日 下午5:08:00
 */
@SuppressWarnings("serial")
public class PageInfoVo<T> extends PageInfo<T> {
	
	public PageInfoVo(){
		super();
	}
	
	public PageInfoVo(List<T> list){
		super(list);	
	}
	
	public static RowBounds toRowBounds(int pageNum, int pageSize) {
		return new RowBounds(pageNum, pageSize);
	}

	public static RowBounds toRowBounds(PageInfo<?> info) {
		return new RowBounds(info.getPageNum(), info.getPageSize());
	}
	
	public static <T> PageInfo<T> of(PageInfo<?> pageInfo,List<T> list,long total){
		PageInfo<T> page = new PageInfo<T>();
		BeanUtils.copyProperties(pageInfo, page);
		page.setTotal(total);
		page.setList(list);
		return page;
	}
	
	public RowBounds toRowBounds() {
		return new RowBounds(this.getPageNum(), this.getPageSize());
	}
	
	public PageInfoVo<?> with(List<?> list){
		return new PageInfoVo<>(list);
	}
	public PageInfoVo<?> with(List<?> list,long total){
		PageInfoVo<?> pageVo = new PageInfoVo<>(list);
		pageVo.setTotal(total);
		return pageVo;
	}
}
