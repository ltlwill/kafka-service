package com.efe.kafkaservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.efe.kafkaservice.request.ApplicationInterceptor;
import com.efe.kafkaservice.util.OSUtils;

@Configuration
public class ApplicationConfiguration extends WebMvcConfigurationSupport {
	
	@Autowired
	private EnvironmentProperties envProperties;

	/**
	 * 增加自定义静态资源配置
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 将磁盘上的文件资源映射为静态资源（类似于tomcat的虚拟目录）,可添加多个（注：需以file:开头，/结尾）
		registry.addResourceHandler(envProperties.getVirtualPathPattern())
				.addResourceLocations(
						OSUtils.isLinuxOS() ? envProperties
								.getLinuxVirtualDirPattern() : envProperties
								.getVirtualDirPattern()); // linux 环境使用linux系统目录模式
	}

	/**
	 * 增加默认的视图
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		String entrance = envProperties.getAppEntrance();
		if (entrance != null && !"".equals(entrance.trim())) {
			entrance = entrance.lastIndexOf(".") > -1 ? entrance.substring(0,
					entrance.lastIndexOf(".")) : entrance;
		}
		registry.addViewController("/").setViewName(entrance);
	}

	/**
	 * 增加拦截器
	 */
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ApplicationInterceptor())
				.addPathPatterns("/**").excludePathPatterns("");
		// registry.addInterceptor(null).addPathPatterns("").excludePathPatterns("");
	}
}
