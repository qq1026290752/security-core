package cn.yichao.security.core.properties;

import lombok.Data;

@Data
public class SocialProperties{
	//qq登录
	private QQProperties qq = new QQProperties();
	//前缀
	private String filterProcessesUrl = "/auth";
	//微信登录
	private WXProperties wx = new WXProperties();
}
