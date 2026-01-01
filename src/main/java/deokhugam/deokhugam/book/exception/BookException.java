package deokhugam.deokhugam.book.exception;

import deokhugam.deokhugam.global.exception.DeokhugamException;
import deokhugam.deokhugam.global.exception.ErrorCode;

public class BookException extends DeokhugamException {
	public BookException(ErrorCode errorCode) {
		super(errorCode);
	}

	public BookException(ErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}

}
