package cn.yichao.security.core.properties;

import lombok.Data;

@Data
public class OAuth2ClientProperties {

	
	private String clientId;
	
	private String clientSecret;
	//默认值为0 没有过期时间 
	private Integer accessTokenValiditySeconds = 7200;
}
