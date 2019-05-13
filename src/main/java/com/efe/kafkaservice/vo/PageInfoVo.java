package com.efe.kafkaservice.vo;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;

import com.github.pagehelper.PageInfo;

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
	
	public PageInfoVo<T> with(List<T> list){
		return new PageInfoVo<>(list);
	}
	public PageInfoVo<T> with(List<T> list,long total){
		this.setTotal(total);
		this.setList(list);
		return this;
	}
}
