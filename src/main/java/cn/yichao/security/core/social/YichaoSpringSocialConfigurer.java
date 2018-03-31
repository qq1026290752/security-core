package cn.yichao.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class YichaoSpringSocialConfigurer extends SpringSocialConfigurer {

	private String filterProcessesUrl;
	
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T postProcess(T object) { 
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl(filterProcessesUrl);
		return (T) filter;
	}
}
