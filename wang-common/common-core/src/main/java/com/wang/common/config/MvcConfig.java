package com.wang.common.config;

import com.wang.common.interceptor.FileUploadInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wang
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/").setCachePeriod(24 * 60 * 60);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FileUploadInterceptor()).addPathPatterns("/**");
    }

    @Bean(value = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME)
    public StandardServletMultipartResolver commonsMultipartResolver() {
        return new StandardServletMultipartResolver();
    }

}
