package deokhugam.deokhugam.user.exception;

import deokhugam.deokhugam.global.exception.DeokhugamException;
import deokhugam.deokhugam.global.exception.ErrorCode;

public class UserException extends DeokhugamException {
	public UserException(ErrorCode errorCode){ super(errorCode); }

	public UserException(ErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}

}
