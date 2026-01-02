package deokhugam.deokhugam.review.exception;

import java.util.UUID;

import deokhugam.deokhugam.global.exception.ErrorCode;

public class ReviewPermissionException extends ReviewException {
	public ReviewPermissionException() {
		super(ErrorCode.REVIEW_ACCESS_DENIED);
	}

	public static ReviewPermissionException withUserAndBook(UUID userId, UUID reviewId) {
		ReviewPermissionException exception = new ReviewPermissionException();
		exception.addDetail("userId", userId);
		exception.addDetail("reviewId", reviewId);
		return exception;
	}
}
