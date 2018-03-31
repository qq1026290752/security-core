package cn.yichao.security.core.social.wx.api;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WXImpl extends AbstractOAuth2ApiBinding implements WX {

private static final ObjectMapper MAPPER = new ObjectMapper(); 
 
	//获取OPEN_ID
	private static final String URL_GET_OPENID = "https://api.weixin.qq.com/cgi-bin/user/info?&openid=%s&lang=zh_CN"; 
	
	public WXImpl(String accessToken) {
		super(accessToken,TokenStrategy.ACCESS_TOKEN_PARAMETER);//accessToken计入参数
	}
	
	
	/**
	 * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
	 */
	protected List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
		messageConverters.remove(0);
		messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return messageConverters;
	}
	/**
	 * 获取用户信息
	 */
	@Override
	public WXUserProfile getWXUserInfo(String openId) {
		String url = String.format(URL_GET_OPENID, openId);
		String response = getRestTemplate().getForObject(url, String.class);
		if(StringUtils.contains(response, "errcode")) {
			return null;
		}
		@SuppressWarnings("unused")
		WXUserProfile profile = null;
		try {
			profile = MAPPER.readValue(response, WXUserProfile.class);
		} catch (Exception e) {
			new RuntimeException("微信获取用户异常。。");
		}
		return null;
	}

}
