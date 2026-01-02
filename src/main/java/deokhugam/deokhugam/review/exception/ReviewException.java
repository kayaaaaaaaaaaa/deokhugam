package deokhugam.deokhugam.review.exception;

import deokhugam.deokhugam.global.exception.DeokhugamException;
import deokhugam.deokhugam.global.exception.ErrorCode;

public class ReviewException extends DeokhugamException {
	public ReviewException(ErrorCode errorCode) {
		super(errorCode);
	}

	public ReviewException(ErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}

}
