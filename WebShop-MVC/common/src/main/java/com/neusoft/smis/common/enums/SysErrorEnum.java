package com.neusoft.smis.common.enums;

import lombok.Getter;

/**
 * 系统错误枚举
 *
 * @author tangwei
 * @created on 2016-09-15 18:11
 * 18:11
 */
public enum SysErrorEnum {
	SYS_EXCEPTION("00005", "系统异常"),
	JSON_ERROR("10000", "json转换错误");

	@Getter
	private String errorCode;
	@Getter
	private String errorMsg;


	/**
	 * 有参构造
	 *
	 * @param errorCode
	 * @param errorMsg
	 */
	SysErrorEnum(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	/**
	 * 返回错误信息
	 *
	 * @param errorCode
	 * @return
	 */

	public static String getErrorMsg(String errorCode) {
		for (SysErrorEnum sysErrorEnum : SysErrorEnum.values()) {
			if (sysErrorEnum.errorCode.equals(errorCode)) {
				return sysErrorEnum.errorMsg;
			}
		}
		return null;
	}

}
