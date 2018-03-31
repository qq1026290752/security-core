package cn.yichao.security.core.social.qq.api;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author w4837
 * 获取 用户信息 
 */
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

	private static final ObjectMapper MAPPER = new ObjectMapper(); 
	
	private String appId;
	
	private String oppenId;
	//获取OPEN_ID
	private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
	//获取userId
	private static final String URL_GET_USERID = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
	
	public QQImpl(String accessToken,String appId) {
		//请求头ACCESS_TOKEN_PARAMETER 父类自动处理accessToken
		super(accessToken,TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appId = appId;
		String url = String.format(URL_GET_OPENID, accessToken);
		String result = getRestTemplate().getForObject(url, String.class);
		this.oppenId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
	}
	
	@Override
	public QQUserInfo getQQUserInfo(){
		String url = String.format(URL_GET_USERID, appId,oppenId);
		//发送请求,得到用户信息
		String result = getRestTemplate().getForObject(url, String.class);
		try {
			QQUserInfo userMessage = MAPPER.readValue(result, QQUserInfo.class);
			userMessage.setOpenId(oppenId);
			log.info("得到的用户信息为:" + userMessage);
			return userMessage;
		} catch (IOException e) {
			throw new RuntimeException("获取用户信息失败");
		}
	}

}
