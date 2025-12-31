package deokhugam.deokhugam.auth.exception;

import deokhugam.deokhugam.global.exception.ErrorCode;
import deokhugam.deokhugam.user.exception.UserException;

public class DuplicateEmailException extends UserException {

	private DuplicateEmailException() {
		super(ErrorCode.EMAIL_ALREADY_EXISTS);
	}

	public static DuplicateEmailException duplicateEmail(String email) {
		DuplicateEmailException exception = new DuplicateEmailException();
		exception.addDetail("email", email);
		return exception;
	}
}
