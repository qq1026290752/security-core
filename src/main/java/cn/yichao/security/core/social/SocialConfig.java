package cn.yichao.security.core.social;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import cn.yichao.security.core.properties.SecurityPeoperties;

@Configuration
@EnableSocial
public class SocialConfig  extends SocialConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	@Autowired
	private SecurityPeoperties securityPeoperties;
	@Autowired(required = false)
	private ConnectionSignUp connectionSignUp;

	
	@Bean(name = {"usersConnectionRepository"})
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		 JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource,
				 connectionFactoryLocator,
				 Encryptors.noOpText());
		 //判断是否存在connectionSignUp,存在自动注册用户进行登陆 不存在跳转至用户登陆界面
 		 if(connectionSignUp!=null) {
			//默认注册用户
 			usersConnectionRepository.setConnectionSignUp(connectionSignUp);
		 }
		 return usersConnectionRepository;
	}
	
	@Bean
	public SpringSocialConfigurer securitySocialConfigurer() {
		SpringSocialConfigurer socialConfigurer = new YichaoSpringSocialConfigurer( securityPeoperties.getSocial().getFilterProcessesUrl());
		socialConfigurer.signupUrl(securityPeoperties.getBrowser().getSignUpUrl());
 		return socialConfigurer;
	}
	
	/**
	 * spring security 提供工具类 可以拿到social相关信息
	 * @param connectionFactoryLocator
	 * @return
	 */
	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator ) {
		return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
	}
}
