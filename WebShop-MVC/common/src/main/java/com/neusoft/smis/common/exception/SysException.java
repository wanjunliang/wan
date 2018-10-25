package com.neusoft.smis.common.exception;

import com.neusoft.smis.common.enums.SysErrorEnum;
import lombok.Getter;

/**
 * 系统自定义异常
 *
 * @author tangwei
 * @created on 2016-09-15 18:24
 */
public class SysException extends Exception {

	@Getter
	private String errorCode = "";

	@Getter
	private String errorMsg = "";

	@Getter
	private SysErrorEnum sysErrorEnum;

	/**
	 * 有数构造方法
	 *
	 * @param errorCode
	 * @param errorMsg
	 */
	public SysException(String errorCode, String errorMsg) {
		super("[" + errorCode + "]" + errorCode);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	/**
	 * 有参构造方法
	 *
	 * @param sysErrorEnum
	 */
	public SysException(SysErrorEnum sysErrorEnum) {
		super("[" + sysErrorEnum.getErrorCode() + "]" + sysErrorEnum.getErrorMsg());
		this.sysErrorEnum = sysErrorEnum;
		this.errorCode = sysErrorEnum.getErrorCode();
		this.errorMsg = sysErrorEnum.getErrorMsg();
	}

	/**
	 * 有参构造方法
	 *
	 * @param msg
	 */
	public SysException(String msg) {
		super(msg);
	}

}
