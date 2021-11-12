package com.electronicapproval.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.electronicapproval.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Autowired
	private PermissionInterceptor interceptor;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/home/home_list_view");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
		.addPathPatterns("/**") // "/**" 손주를 포함한 모든 디렉토리 확인
		.excludePathPatterns("/user/sign_out", "/static/**", "/error"); // 인터셉터를 안태울 path 설정
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload_files/**")
		.addResourceLocations("file:////home/ec2-user/upload_files/");
		registry.addResourceHandler("/upload_images/**")
		.addResourceLocations("file:////home/ec2-user/upload_images/");
	}
}
