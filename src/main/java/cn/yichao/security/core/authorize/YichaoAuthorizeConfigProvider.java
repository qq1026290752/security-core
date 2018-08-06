package cn.yichao.security.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import cn.yichao.security.core.constant.ProjectConstant;
import cn.yichao.security.core.properties.SecurityPeoperties;
@Component
@Order(Integer.MIN_VALUE)
public class YichaoAuthorizeConfigProvider implements AuthorizeConfigProvider {

	@Autowired
	private SecurityPeoperties securityPeoperties;
	
	@Override
	public void config(
			ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests) {
		 authorizeRequests.antMatchers(
				 	ProjectConstant.LOGIN_JUMP_CONTROLLER
		 			,securityPeoperties.getBrowser().getLoginPage()
		 			,ProjectConstant.VALIDATE_URI_PREFIX + "*"
		 			,securityPeoperties.getBrowser().getSignUpUrl()
		 			,securityPeoperties.getSession().getSessoinInvalidPage())
		 .permitAll();
		 
	}

}
