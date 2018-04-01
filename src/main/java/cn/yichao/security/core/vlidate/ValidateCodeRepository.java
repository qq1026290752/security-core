package cn.yichao.security.core.vlidate;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeRepository {
	/**
	 * 保存验证码
	 * @param request
	 * @param validateCode
	 * @param validateCodeTyp
	 */
	void save(ServletWebRequest request,ValidateCode validateCode,String validateCodeKey);
	/**
	 * 获取验证码
	 * @param request
	 * @param validateCode
	 */
	ValidateCode get(ServletWebRequest request,String validateCodeKey);
	/**
	 * 删除验证码
	 * @param validateCode
	 * @param validateCodeTyp
	 */
	void remove(ServletWebRequest request,String validateCodeKey);
}
