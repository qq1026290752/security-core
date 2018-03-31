package cn.yichao.security.core.social.qq.connet;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import cn.yichao.security.core.social.qq.api.QQ;
import cn.yichao.security.core.social.qq.api.QQUserInfo;

public class QQAdapter implements ApiAdapter<QQ> {
	
	//测试QQ是否可以通信
	@Override
	public boolean test(QQ api) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo qqUserInfo = api.getQQUserInfo();
		values.setDisplayName(qqUserInfo.getNickname());//用户名
		values.setImageUrl(qqUserInfo.getFigureurl_qq_1());//qq头像
		values.setProfileUrl(null);//个人主页
		values.setProviderUserId(qqUserInfo.getOpenId());
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		// TODO Auto-generated method stub
		
	}

}
