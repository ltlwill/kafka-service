package com.efe.kafkaservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseModel {
	// 姓名
	private String name;
	// 年龄
	private Integer age;
	// 电话号码
	private String phoneNo;
	// 邮箱
	private String email;

}
