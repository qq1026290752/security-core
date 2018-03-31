package cn.yichao.security.core.social.wx.connet;

import org.springframework.social.oauth2.AccessGrant;

import lombok.Data;
import lombok.EqualsAndHashCode; 
@Data
@EqualsAndHashCode(callSuper = false)
public class WxAccessGrant extends AccessGrant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String openId;
	
	
	public WxAccessGrant() {
		super("");
	}
	
	public WxAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
		super(accessToken,scope,refreshToken,expiresIn);
	}

	
}
