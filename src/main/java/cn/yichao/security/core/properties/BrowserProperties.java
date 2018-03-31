 package cn.yichao.security.core.properties;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component(value = "browser")
@Data
public class BrowserProperties {
	
	private String loginPage = "/signIn.html";
	
	private LoginType loginType = LoginType.JSON ;
	//记住我
	private Integer rememberMeSeconds = 3600;//秒
	//登录页
	private String signUpUrl = "/default-regist.html";
	
	private String signOutHtml;
	

	
}
