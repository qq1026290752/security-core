package cn.yichao.security.core.social.wx.connet;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider; 
 
import cn.yichao.security.core.social.wx.api.WX;
import cn.yichao.security.core.social.wx.api.WXImpl;

public class WeixinServiceProvider extends AbstractOAuth2ServiceProvider<WX>{
  
	//获取授权码
	private static final String  URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
	private static final String  URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	public WeixinServiceProvider(String appId,String appSecret) {
		super(new WeixinAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
	}

	@Override
	public WX getApi(String accessToken) {
		// TODO Auto-generated method stub
		return new WXImpl(accessToken);
	}

	
	 

}
