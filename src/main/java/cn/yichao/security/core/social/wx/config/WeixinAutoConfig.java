package cn.yichao.security.core.social.wx.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

import cn.yichao.security.core.config.auto.SocialAutoConfigurerAdapter;
import cn.yichao.security.core.properties.SecurityPeoperties;
import cn.yichao.security.core.social.wx.connet.WeixinConnectionFactory;
import cn.yichao.security.core.view.YichaoConnectionView; 
@Configuration
//配置文件存在 且有appId
@ConditionalOnProperty(prefix = "yichao.security.social.wx",name =  "app-id")
public class WeixinAutoConfig extends SocialAutoConfigurerAdapter {
	
	@Autowired
	private SecurityPeoperties securityPeoperties;

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		// TODO Auto-generated method stub
		return new WeixinConnectionFactory(securityPeoperties.getSocial().getWx().getProviderId()
				, securityPeoperties.getSocial().getWx().getAppId(), securityPeoperties.getSocial().getWx().getAppSecret());
	}

	@Override
	public UserIdSource getUserIdSource() {
		 return new UserIdSource() {
			
			@Override
			public String getUserId() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}	
	
	/**
	 * 默认绑定跳转
	 * @return
	 */
	@Bean({"/connect/wxConnected","/connect/wxConnect"})
	@ConditionalOnMissingBean(name = "wxConnetectionView")
	public View wxConnetView() {
		return new YichaoConnectionView();
	}
}
