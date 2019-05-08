package com.efe.kafkaservice.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * <p>基础实体类: </p> 
 * @author Liu TianLong
 * 2019年5月7日 下午5:56:11
 */
@SuppressWarnings("serial")
@Getter
@Setter
public class BaseModel implements Serializable,Cloneable{
	
	private String id;

}
