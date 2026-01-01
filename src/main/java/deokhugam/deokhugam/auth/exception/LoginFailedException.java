package deokhugam.deokhugam.auth.exception;

import deokhugam.deokhugam.global.exception.ErrorCode;
import deokhugam.deokhugam.user.exception.UserException;

public class LoginFailedException extends UserException {
	public LoginFailedException() {
		super(ErrorCode.LOGIN_INPUT_INVALID);
	}

	public static LoginFailedException invalidIdOrPassword() {
		return new LoginFailedException();
	}
}
