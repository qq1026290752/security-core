package cn.yichao.security.core.social.wx.connet;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
 
import cn.yichao.security.core.social.wx.api.WX;
import cn.yichao.security.core.social.wx.api.WXUserProfile;

public class WeixinAdapter implements ApiAdapter<WX> {
	
	private String openId;
	
	public WeixinAdapter(String openId) {
		this.openId = openId;
	}
	public WeixinAdapter( ) {}

	//测试QQ是否可以通信
	@Override
	public boolean test(WX api) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setConnectionValues(WX api, ConnectionValues values) {
		WXUserProfile wxUserInfo = api.getWXUserInfo(openId);
		values.setDisplayName(wxUserInfo.getNickname());//用户名
		values.setImageUrl(wxUserInfo.getHeadimgurl());//qq头像
		values.setProfileUrl(null);//个人主页 
	}

	@Override
	public UserProfile fetchUserProfile(WX api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(WX api, String message) {
		// TODO Auto-generated method stub
		
	}

}
