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
public class PageInfoVO<T> extends PageInfo<T> {
	
	public PageInfoVO(){
		super();
	}
	
	public PageInfoVO(List<T> list){
		super(list);	
	}
	
	public PageInfoVO(List<T> list,int navigatePages){
		super(list,navigatePages);	
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
	
	public PageInfoVO<?> with(List<?> list){
		return new PageInfoVO<>(list);
	}
	public PageInfoVO<?> with(List<?> list,long total){
		PageInfoVO<?> pageVo = new PageInfoVO<>(list);
		pageVo.setTotal(total);
		return pageVo;
	}
}
