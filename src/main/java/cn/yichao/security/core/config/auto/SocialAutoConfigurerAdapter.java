package cn.yichao.security.core.config.auto;

import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

public abstract class SocialAutoConfigurerAdapter extends SocialConfigurerAdapter {

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
			Environment environment) {
		configurer.addConnectionFactory(createConnectionFactory());
	}

	protected abstract ConnectionFactory<?> createConnectionFactory();

}
