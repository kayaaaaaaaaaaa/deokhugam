package deokhugam.deokhugam.review.exception;

import java.util.UUID;

import deokhugam.deokhugam.global.exception.ErrorCode;

public class ReviewAlreadyExistsException extends ReviewException {

	public ReviewAlreadyExistsException() {
		super(ErrorCode.REVIEW_ALREADY_EXISTS);
	}

	public static ReviewAlreadyExistsException withUserAndBook(UUID userId, UUID bookId) {
		ReviewAlreadyExistsException exception = new ReviewAlreadyExistsException();
		exception.addDetail("userId", userId);
		exception.addDetail("bookId", bookId);
		return exception;
	}
}
