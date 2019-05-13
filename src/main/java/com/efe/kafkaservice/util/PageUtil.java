package com.efe.kafkaservice.util;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.github.pagehelper.PageInfo;

/**
 * 分页工具类
 * 
 * @author liutianlong
 *
 */
public final class PageUtil {

	public static RowBounds toRowBounds(int pageNum, int pageSize) {
		return new RowBounds(pageNum, pageSize);
	}

	public static RowBounds toRowBounds(PageInfo<?> info) {
		return new RowBounds(info.getPageNum(), info.getPageSize());
	}
	
	public static <T> PageInfo<T> toPageInfo(List<T> list,long total){
		PageInfo<T> page = new PageInfo<T>(list);
		page.setTotal(total);
		return page;
	}

}
