package cn.yichao.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

public class WXProperties extends SocialProperties {

	private String providerId = "wx";

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	
	
}
