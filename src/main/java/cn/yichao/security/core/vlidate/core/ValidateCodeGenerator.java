package cn.yichao.security.core.vlidate.core;

import javax.servlet.ServletRequest;
 
import cn.yichao.security.core.vlidate.ValidateCode;

/**
 * 验证码实现接口
 * @author w4837
 *
 */
public interface ValidateCodeGenerator {
	/**
	 * 图片验证码实现接口
	 * @param request
	 * @return
	 */
	 ValidateCode createImageCode(ServletRequest request);
}
