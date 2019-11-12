package com.efe.kafkaservice.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.efe.kafkaservice.util.Constant;


/**
 * 
 * <p>基础控制器: </p> 
 * @author Liu TianLong
 * 2019年4月23日 上午10:23:42
 */
public class BaseController {

	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	public Map<String, Object> ajaxSuccess(Map<String, Object> resultMap) {
        resultMap.put("statusCode", Constant.StatusCode.HTTP_SUCCESS);
        return resultMap;
    }

    public Map<String, Object> ajaxError(Map<String, Object> resultMap) {
        resultMap.put("statusCode", Constant.StatusCode.HTTP_ERROR);
        return resultMap;
    }
}
