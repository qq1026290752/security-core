package cn.yichao.security.core.social.wx.connet;

import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class WeixinAuth2Template extends OAuth2Template {

	private String clientId;
	private String clientSecret;
	private String accessTokenUrl;
	
	private final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_toke";
	
	public static final ObjectMapper MAPPER = new ObjectMapper();
	

	public WeixinAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		setUseParametersForClientAuthentication(true);
		this.accessTokenUrl = accessTokenUrl;
		this.clientId =  clientId;
		this.clientSecret = clientSecret;
	}

	/**
	 * 微信OAuth2登录不符合标准的OAuth 所以要自行拼装Url
	 * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
	 */
	@Override
	public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri,
			MultiValueMap<String, String> additionalParameters) {
		StringBuilder oAuthAccessUrl = new StringBuilder(accessTokenUrl);
		oAuthAccessUrl.append("?appid="+ clientId);
		oAuthAccessUrl.append("&secret=" + clientSecret);
		oAuthAccessUrl.append("&code=" + authorizationCode);
		oAuthAccessUrl.append("&grant_type=authorization_code");
		return getOAuth2Access(oAuthAccessUrl);
	}
	/**
	 * 封装Url 得到 refreshToke
	 * https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
	 */
	@Override
	public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
		StringBuilder oAuthAccessUrl = new StringBuilder(REFRESH_TOKEN_URL);
		oAuthAccessUrl.append("?appid="+ clientId);
		oAuthAccessUrl.append("&grant_type=authorization_code");
		oAuthAccessUrl.append("&refresh_token="+ refreshToken);
		return getOAuth2Access(oAuthAccessUrl);
	}
	
 
	
	//获取OathAccessToken
	@SuppressWarnings("unchecked")
	private AccessGrant getOAuth2Access(StringBuilder oAuthAccessUrl) {
		log.info("需要请求的URL为:" + oAuthAccessUrl.toString());
		String accessResponse = getRestTemplate().getForObject(oAuthAccessUrl.toString(),String.class);
		log.info("获取到响应为:" + accessResponse);
		Map<String, Object> result = null;
		try {
			result = MAPPER.readValue(accessResponse, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回错误码时直接返回空
		if(StringUtils.isNotBlank(MapUtils.getString(result, "errcode"))){
			String errcode = MapUtils.getString(result, "errcode");
			String errmsg = MapUtils.getString(result, "errmsg");
			throw new RuntimeException("获取access token失败, errcode:"+errcode+", errmsg:"+errmsg);
		}
		
		WxAccessGrant accessToken = new WxAccessGrant(
				MapUtils.getString(result, "access_token"), 
				MapUtils.getString(result, "scope"), 
				MapUtils.getString(result, "refresh_token"), 
				MapUtils.getLong(result, "expires_in"));
		
		accessToken.setOpenId(MapUtils.getString(result, "openid"));
		
		return accessToken;
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
