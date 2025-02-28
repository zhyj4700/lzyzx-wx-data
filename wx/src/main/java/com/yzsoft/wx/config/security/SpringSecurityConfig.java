package com.yzsoft.wx.config.security;

import com.yzsoft.wx.config.security.filter.CheckTokenFilter;
import com.yzsoft.wx.config.security.handler.AnonymousAuthenticationHandler;
import com.yzsoft.wx.config.security.handler.CustomerAccessDeniedHandler;
import com.yzsoft.wx.config.security.handler.LoginFailureHandler;
import com.yzsoft.wx.config.security.handler.LoginSuccessHandler;
import com.yzsoft.wx.config.security.service.CustomerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private CustomerUserDetailsService customerUserDetailsService;
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFailureHandler loginFailureHandler;
    @Resource
    private AnonymousAuthenticationHandler anonymousAuthenticationHandler;
    @Resource
    private CustomerAccessDeniedHandler customerAccessDeniedHandler;

    @Resource
    private CheckTokenFilter checkTokenFilter;    /*
     * 注入加密处理类
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
        //登录前进行过滤
        http.addFilterBefore(checkTokenFilter, UsernamePasswordAuthenticationFilter.class).
                formLogin()
                .loginProcessingUrl("/api/user/login")
        // 设置登录验证成功或失败后的的跳转地址
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .usernameParameter("username")
        // 禁用csrf防御机制
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/user/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(anonymousAuthenticationHandler)
                .accessDeniedHandler(customerAccessDeniedHandler)
                .and().cors();//开启跨域配置
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
