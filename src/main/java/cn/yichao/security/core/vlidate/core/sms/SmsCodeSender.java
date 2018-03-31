package cn.yichao.security.core.vlidate.core.sms;

public interface SmsCodeSender {
	/**
	 * 发送验证码
	 * @param mobile
	 * @param code
	 */
	void send(String mobile,String code);
}
