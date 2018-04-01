package cn.yichao.security.core.constant;

public interface ProjectConstant {

	String IMAGE_SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
    String SMS_SESSION_KEY = "SESSION_KEY_SMS_CODE";	
    String IMAGE_CODE = "image_code";
	//登录路径
	String LOGIN_URL = "/authentication/form";
	//短信登录路径
	String SMS_LOGIN_URL = "/authentication/mobile";
	//登录页面跳转
	String LOGIN_JUMP_CONTROLLER = "/authebtication/require";
	
	String VALIDATE_URI_PREFIX = "/vlidate/";

	String SESSION_INVALID = "/session/invalid";
	//返回标准页面
	String CONTENTTYPE_HTML = "text/html;charset=UTF-8";
	//返回JSON信息
	String CONTENTTYPE_JSON = "application/json;charset=UTF-8";
	//appSocial注册地址
	String SOCIAL_SIGNUP_URI = "/social/signUp";
}

