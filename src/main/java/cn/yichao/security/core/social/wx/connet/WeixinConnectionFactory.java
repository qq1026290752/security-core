package cn.yichao.security.core.social.wx.connet;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

import cn.yichao.security.core.social.wx.api.WX;

public class WeixinConnectionFactory extends OAuth2ConnectionFactory<WX> {

	public WeixinConnectionFactory(String providerId,String appId, String appSecret) {
		super(providerId, new WeixinServiceProvider(appId, appSecret), new WeixinAdapter());
	}

	//抽取OpenId
	@Override
	protected String extractProviderUserId(AccessGrant accessGrant) {
		if(accessGrant instanceof WxAccessGrant) {
			return ((WxAccessGrant) accessGrant).getOpenId();
		}
		return null;
	}
	
	@Override
	public Connection<WX> createConnection(AccessGrant accessGrant) {
		// TODO Auto-generated method stub
		return new OAuth2Connection<WX>(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
				accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
	}
	
	
	private OAuth2ServiceProvider<WX> getOAuth2ServiceProvider() {
		return (OAuth2ServiceProvider<WX>) getServiceProvider();
	}
	
	
	private ApiAdapter<WX> getApiAdapter(String providerUserId) {
		return new WeixinAdapter(providerUserId);
	}
}
