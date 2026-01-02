package deokhugam.deokhugam.user.exception;

import java.util.UUID;

import deokhugam.deokhugam.global.exception.ErrorCode;

public class UserPermissionException extends UserException {
	public UserPermissionException() {
		super(ErrorCode.USER_ACCESS_DENIED);
	}

	public static UserPermissionException withPathUserIdAndLoginUserId(UUID pathUserId, UUID loginUserId) {
		UserPermissionException exception = new UserPermissionException();
		exception.addDetail("pathUserId", loginUserId);
		exception.addDetail("userId", loginUserId);
		return exception;
	}
}
