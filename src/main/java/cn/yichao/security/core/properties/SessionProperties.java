package cn.yichao.security.core.properties;

import cn.yichao.security.core.constant.ProjectConstant;
import lombok.Data;

@Data
public class SessionProperties {

	//如果session存储不支持其他人登录
	private boolean maxSessionsPreventsLogin = true;
	//最大并发个数
	private Integer maximumSessions = 1;
	//session失效跳转地址或者页面
	private String sessoinInvalidPage = ProjectConstant.SESSION_INVALID;
}
