package com.yzsoft.wx.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcSupport implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //当访问/static/??? 下的路径时不要走mvc
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

}
