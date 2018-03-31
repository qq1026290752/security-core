package cn.yichao.security.core.vlidate.core.sms;

import lombok.extern.slf4j.Slf4j;
/**
 * 默认发送验证码逻辑
 * @author w4837
 *
 */
@Slf4j
public class DefaultSMSCodeSender implements SmsCodeSender {

	@Override
	public void send(String mobile, String code) {
		log.info("向手机号:" + mobile +"发送了验证码,验证码是" + code);
	}

}
