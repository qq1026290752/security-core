package cn.yichao.security.core.social.qq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.ConnectionFactory;

import cn.yichao.security.core.properties.SecurityPeoperties;
import cn.yichao.security.core.social.qq.connet.QQConnectionFactory; 
@Configuration
//配置文件存在 且有appId
@ConditionalOnProperty(prefix = "yichao.security.social.qq",name =  "app-id")
public class QQAutoConfig extends cn.yichao.security.core.config.auto.SocialAutoConfigurerAdapter {
	
	@Autowired
	private SecurityPeoperties securityPeoperties;

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		// TODO Auto-generated method stub
		return new QQConnectionFactory(securityPeoperties.getSocial().getQq().getProviderId()
				, securityPeoperties.getSocial().getQq().getAppId(), securityPeoperties.getSocial().getQq().getAppSecret());
	}

	
	@Override
	public UserIdSource getUserIdSource() {
		// TODO Auto-generated method stub
		return super.getUserIdSource();
	}
}
