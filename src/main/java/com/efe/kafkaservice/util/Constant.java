package com.efe.kafkaservice.util;

public class Constant {

    // http响应状态码
    public static class StatusCode {
        public final static String HTTP_SUCCESS = "200";// 响应成功
        public final static String HTTP_ERROR = "500";// 内部服务器响应失败
    }
    
    public static class ProcessStatus {
    	public final static String TASK_INIT = "0";
    	public final static String GET_DATA_END = "1";
    	public final static String UPDATING_DATA = "2";
    	public final static String TASK_END = "3";
    }

    public static class OperateType {
        public final static String UPDATE_ALL_ACCOUNT = "0";;
        public final static String UPDATE_SPECIFIC_ACCOUNT = "1";
    }
}
