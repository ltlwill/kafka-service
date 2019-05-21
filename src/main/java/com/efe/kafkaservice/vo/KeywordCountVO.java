package com.efe.kafkaservice.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor
public class KeywordCountVO extends BaseVO{
	
	private String category;
	
	private String word;
	
	private Long count;
	
	
	@Override
	public String toString(){
		return "category:" + this.category + ",word:" + this.word + ",count:" + this.count; 
	}

}
