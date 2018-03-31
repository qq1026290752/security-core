package cn.yichao.security.core.social.qq.connet;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider; 

import cn.yichao.security.core.social.qq.api.QQ;
import cn.yichao.security.core.social.qq.api.QQImpl;
import cn.yichao.security.core.social.qq.api.QQOAut2Template;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ>{
 
	private String appId;
	//获取授权码
	private static final String  URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
	private static final String  URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
	
	public QQServiceProvider(String appId,String appSecret) {
		super(new QQOAut2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		this.appId = appId;
	}

	
	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}

}
