package com.qxcwl.tooltip.web.exception;

/**
 * 重复提交异常
 *
 * @Author 曲修成
 * @ClassName ResubmitException
 * @Description
 * @Date 2016-12-07 20:47:00
 */
public class ResubmitException extends RuntimeException{


	private static final long serialVersionUID = -3716229947895140578L;

	public ResubmitException(String message) {
		super(message);
	}
}
