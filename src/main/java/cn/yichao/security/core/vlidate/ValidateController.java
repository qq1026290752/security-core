package cn.yichao.security.core.vlidate;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import cn.yichao.security.core.constant.ProjectConstant;
import cn.yichao.security.core.vlidate.core.ValidateCodeGenerator;
import cn.yichao.security.core.vlidate.core.sms.SmsCodeSender; 

@RestController
@RequestMapping("vlidate") 
public class ValidateController {
	
	
	/**
	 * 生成图像验证码
	 * 
	 * @param request
	 * @param response
	 */ 
	@Autowired
	private ValidateCodeGenerator imageValidateCodeGenerator;
	@Autowired
	private ValidateCodeGenerator smsValidateCodeGenerator;
	@Autowired
	private SmsCodeSender smsCodeSender;

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	/**
	 * 生成图片验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@GetMapping("image")
	public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ImageCode imageCode = (ImageCode)imageValidateCodeGenerator.createImageCode(request);
		ValidateCode validateCode = new ValidateCode(imageCode.getCode(), imageCode.getExpireTime());
		sessionStrategy.setAttribute(new ServletWebRequest(request), ProjectConstant.IMAGE_SESSION_KEY, validateCode);
		ImageIO.write(imageCode.getBufferedImage(), "JPEG", response.getOutputStream());
	}
	
	/**
	 * 生成手机验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletRequestBindingException 
	 */
	@GetMapping("sms")
	public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
		ValidateCode smsValidateCode = smsValidateCodeGenerator.createImageCode(request);
		String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
		smsCodeSender.send(mobile,smsValidateCode.getCode());
		sessionStrategy.setAttribute(new ServletWebRequest(request), ProjectConstant.SMS_SESSION_KEY, smsValidateCode);
	}

 
 
}
