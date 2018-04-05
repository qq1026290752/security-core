package cn.yichao.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
 
@ConfigurationProperties(prefix="yichao.security")
@Data
public class SecurityPeoperties {
	
	private BrowserProperties browser = new BrowserProperties();
	
	private ValidataProperties image = new ValidataProperties();
	
	private SmsValidateProperties sms = new SmsValidateProperties();
	
	private SocialProperties social = new SocialProperties();
	
	private SessionProperties session = new SessionProperties();
	
	private OAuth2Properties oauth2 = new OAuth2Properties();
}
