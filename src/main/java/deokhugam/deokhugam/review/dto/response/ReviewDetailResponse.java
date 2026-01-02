package deokhugam.deokhugam.review.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import deokhugam.deokhugam.review.entity.Review;

public record ReviewDetailResponse(
	UUID id,
	UUID userId,
	String userNickname,
	UUID bookId,
	String bookTitle,
	String bookThumbnailUrl,
	String content,
	BigDecimal rating,
	int likeCount,
	int commentCount,
	boolean likedByMe,
	Instant createdAt,
	Instant updatedAt
) {
	public static ReviewDetailResponse of(Review review, boolean likedByMe) {
		return new ReviewDetailResponse(
			review.getId(),
			review.getUser().getId(),
			review.getUser().getNickname(),
			review.getBook().getId(),
			review.getBook().getTitle(),
			review.getBook().getThumbnailUrl(),
			review.getContent(),
			review.getRating(),
			review.getLikeCount(),
			review.getCommentCount(),
			likedByMe,
			review.getCreatedAt(),
			review.getUpdatedAt()
		);
	}

	public static ReviewDetailResponse of(Review review) {
		return of(review, false);
	}
}
