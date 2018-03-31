package cn.yichao.security.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
@Component
public class SmsAuthentioncationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Autowired
	private AuthenticationSuccessHandler yichaoAuthenticationSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler yichaoAuthenticationFailuHandler;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		SMSAuthenticationFilter smsAuthenticationFilter = new SMSAuthenticationFilter();
		smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		//成功跳转
		smsAuthenticationFilter.setAuthenticationSuccessHandler(yichaoAuthenticationSuccessHandler);
		//失败跳转
		smsAuthenticationFilter.setAuthenticationFailureHandler(yichaoAuthenticationFailuHandler);
		//
		SMSAuthenticationProvider smsAuthenticationProvider = new SMSAuthenticationProvider();
		smsAuthenticationProvider.setUserDetailsService(userDetailsService);
		http.authenticationProvider(smsAuthenticationProvider)
			.addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);//短信验证码加入过滤器
	}
}
