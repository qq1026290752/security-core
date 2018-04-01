package cn.yichao.security.core.vlidate.core;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.util.AntPathMatcher;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.yichao.security.core.constant.ProjectConstant;
import cn.yichao.security.core.properties.SecurityPeoperties;
import cn.yichao.security.core.vlidate.ImageCode;
import cn.yichao.security.core.vlidate.ValidateCodeRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class ValidateCodeFiler extends OncePerRequestFilter implements InitializingBean {


	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	 
	private AuthenticationFailureHandler yichaoAuthenticationFailuHandler;
	//存放所有验证码路径
	private Set<String> urls = new HashSet<>();
	
	@Autowired
	private ValidateCodeRepository validateCodeRepository;
	
	private SecurityPeoperties securityPeoperties;
	//路径匹配器
	private AntPathMatcher antPathMatcher = new AntPathMatcher(); 
	
	
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		//得到需要验证码拦截的URL
		String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityPeoperties.getImage().getUrl(), ",");
		if(!ObjectUtils.equals(configUrls, null)) {			
			for (String configUrl : configUrls) {
				urls.add(configUrl);
			}
		}
		urls.add(ProjectConstant.LOGIN_URL);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		boolean action = false;
		for (String validataUrl : urls) {
			//判断当前路径是否需要验证
			if(antPathMatcher.match(request.getRequestURI(), validataUrl)) {
				action = true;
			}
		}
		
		// TODO Auto-generated method stub
		if(action) {
			try {
				validata(new ServletWebRequest(request));
			} catch (ValidataCodeException exception) { 
				log.error("图形验证码验证错误,错误原因是:" + exception.getMessage());
				yichaoAuthenticationFailuHandler.onAuthenticationFailure(request, response, exception);
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	private void validata(ServletWebRequest request) throws ServletRequestBindingException {
		  ImageCode code = (ImageCode)validateCodeRepository.
	                get(request, ProjectConstant.IMAGE_SESSION_KEY);
	        String imageCode = ServletRequestUtils.getStringParameter(request.getRequest(), ProjectConstant.IMAGE_CODE);
	        if(StringUtils.isBlank(imageCode)){
	            throw  new ValidataCodeException("验证码不能为空");
	        }
	        if(code == null){
	            throw new ValidataCodeException("验证码不存在");
	        }
	        if(code.isExpried()){
	            sessionStrategy.removeAttribute(request,ProjectConstant.IMAGE_SESSION_KEY);
	            throw new ValidataCodeException("当前验证码已过期");
	        }
	        if(!StringUtils.equals(imageCode,code.getCode())){
	            throw new ValidataCodeException("验证码输入错误");
	        }
	        sessionStrategy.removeAttribute(request,ProjectConstant.IMAGE_SESSION_KEY);
	}

}
