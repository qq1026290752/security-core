package cn.yichao.security.core.properties;

import lombok.Data;

@Data
public class SmsValidateProperties {

    //验证码保存时间
	private int expireIn = 120;  
	// 验证码长度
	private int codeLen = 6;
	//针对的路径
	private String url;
}
