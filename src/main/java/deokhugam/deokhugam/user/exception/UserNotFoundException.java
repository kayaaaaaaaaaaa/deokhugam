package deokhugam.deokhugam.user.exception;

import java.util.UUID;

import deokhugam.deokhugam.auth.exception.LoginFailedException;
import deokhugam.deokhugam.global.exception.ErrorCode;

public class UserNotFoundException extends UserException {


	public UserNotFoundException() {
		super(ErrorCode.USER_NOT_FOUND);
	}

	public static UserNotFoundException withId(UUID id) {
		UserNotFoundException exception = new UserNotFoundException();
		exception.addDetail("id", id);
		return exception;
	}

	public static UserNotFoundException withEmail(String email) {
		UserNotFoundException exception = new UserNotFoundException();
		exception.addDetail("email", email);
		return exception;
	}
}
