package cn.yichao.security.core.vlidate.core.sms;


import javax.servlet.ServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.yichao.security.core.properties.SecurityPeoperties;
import cn.yichao.security.core.vlidate.ValidateCode;
import cn.yichao.security.core.vlidate.core.ValidateCodeGenerator;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component("smsValidateCodeGenerator")
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {
	
	@Autowired
	private SecurityPeoperties securityPeoperties;
	
	@Override
	public ValidateCode createImageCode(ServletRequest request) {
		String code = RandomStringUtils.randomNumeric(securityPeoperties.getSms().getCodeLen());
        log.info("生成的验证码为:" + code);
        return new ValidateCode(code,securityPeoperties.getSms().getExpireIn());
    }

	public SecurityPeoperties getSecurityPeoperties() {
		return securityPeoperties;
	}

	public void setSecurityPeoperties(SecurityPeoperties securityPeoperties) {
		this.securityPeoperties = securityPeoperties;
	}
	
	
	
}
