package cn.yichao.security.core.vlidate.core;

import org.springframework.security.core.AuthenticationException;

public class ValidataCodeException extends AuthenticationException {

	public ValidataCodeException(String msg) {
		super(msg);  
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
