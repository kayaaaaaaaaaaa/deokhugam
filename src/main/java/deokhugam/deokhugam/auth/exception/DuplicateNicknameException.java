package deokhugam.deokhugam.auth.exception;

import deokhugam.deokhugam.global.exception.ErrorCode;
import deokhugam.deokhugam.user.exception.UserException;

public class DuplicateNicknameException extends UserException {

	private DuplicateNicknameException() {
		super(ErrorCode.NICKNAME_ALREADY_EXISTS);
	}

	public static DuplicateNicknameException duplicateNickname(String nickname) {
		DuplicateNicknameException exception = new DuplicateNicknameException();
		exception.addDetail("nickname", nickname);
		return exception;
	}
}
