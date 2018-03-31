package cn.yichao.security.core.social.qq.api;

import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class QQOAut2Template extends OAuth2Template {

	public QQOAut2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		setUseParametersForClientAuthentication(true);
	}
	/**
	 * 获取到token
	 */
	@Override
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		//发送请求
		String  responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
		log.info("获取到响应为:"  +  responseStr);
		String[] responses = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");
		String accessToken = StringUtils.substringAfterLast(responses[0], "=");
		String refreshToken = StringUtils.substringAfterLast(responses[2], "=");
		Long expiresIn = new Long(StringUtils.substringAfterLast(responses[1], "="));
		return new AccessGrant(accessToken, null, refreshToken, expiresIn);
	}
	
	/**
	 * 添加 html转换器
	 */
	@Override
	protected RestTemplate createRestTemplate() {
	 RestTemplate template = super.createRestTemplate();
	 template.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
	 return template;
	}

}
