package cn.yichao.security.core.vlidate.core;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.yichao.security.core.properties.SecurityPeoperties;
import cn.yichao.security.core.vlidate.core.sms.DefaultSMSCodeSender;
import cn.yichao.security.core.vlidate.core.sms.SmsCodeSender;

@Configuration
public class ValidateCodeBeaConfig {

	@Autowired
	private SecurityPeoperties securityPeoperties;
	
	@Bean
	//判断系统中是否存在该Bean
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
		ImageValidateCodeGenerator generator = new ImageValidateCodeGenerator();
		generator.setSecurityPeoperties(securityPeoperties);
		return generator;
	}
	
	/**
	 * 短信验证码发送接口
	 * @return
	 */
	
	@Bean
	//判断系统中是否存在该Bean
	@ConditionalOnMissingBean(name = "smsCodeSender")
	public SmsCodeSender smsCodeSender() {
		DefaultSMSCodeSender smsCodeSender = new DefaultSMSCodeSender(); 
		return smsCodeSender;
	}
}
