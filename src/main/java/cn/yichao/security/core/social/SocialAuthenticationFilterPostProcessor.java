package cn.yichao.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

public interface SocialAuthenticationFilterPostProcessor {
	public void proessor(SocialAuthenticationFilter authenticationFilter);
}
