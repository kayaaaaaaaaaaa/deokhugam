package deokhugam.deokhugam.review.exception;

import java.util.UUID;

import deokhugam.deokhugam.global.exception.ErrorCode;

public class ReviewNotFoundException extends ReviewException {

	public ReviewNotFoundException() {
		super(ErrorCode.REVIEW_NOT_FOUND);
	}

	public static ReviewNotFoundException withId(UUID reviewId) {
		ReviewNotFoundException exception = new ReviewNotFoundException();
		exception.addDetail("reviewId", reviewId);
		return exception;
	}
}
