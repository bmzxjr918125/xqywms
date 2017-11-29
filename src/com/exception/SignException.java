package com.exception;
/**
 * <p>ClassName: SignException</p>
 * <p>@Description: 生成Sign时异常</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-8-1上午10:06:47</p>
 */
public class SignException extends RuntimeException{
	private static final long serialVersionUID = 3802121247340897419L;

	public SignException() {
		super();
	}

	public SignException(String message, Throwable cause) {
		super(message, cause);
	}

	public SignException(String message) {
		super(message);
	}

	public SignException(Throwable cause) {
		super(cause);
	}
	
}
