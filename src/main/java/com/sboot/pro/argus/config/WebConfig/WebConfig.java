package com.sboot.pro.argus.config.WebConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sboot.pro.argus.DTO.AccessInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccessInterceptor())
                .addPathPatterns("/report/**", "/blockManagement/**","/sow/**")
                .excludePathPatterns("/login/**", "/resources/**","/argus/**","/include/**");
    }
}

