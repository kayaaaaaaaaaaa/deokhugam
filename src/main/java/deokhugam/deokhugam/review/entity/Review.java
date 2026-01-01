package deokhugam.deokhugam.review.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "reviews")
public class Review {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	private UUID id;

	@Column(name = "user_id", nullable = false)
	private UUID userId;

	@Column(name = "book_id", nullable = false)
	private UUID bookId;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "rating", nullable = false, precision = 2, scale = 1)
	private BigDecimal rating;

	@Column(name = "like_count", nullable = false)
	private int likeCount = 0;

	@Column(name = "comment_count", nullable = false)
	private int commentCount = 0;

	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@Column(name = "updated_at")
	private Instant updatedAt;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted = false;


	/* < 생성 로직 > */

	public static Review create(
		UUID userId,
		UUID bookId,
		String content,
		BigDecimal rating
	) {
		Review review = new Review();

		review.id = UUID.randomUUID();
		review.userId = userId;
		review.bookId = bookId;
		review.content = content;
		review.rating = rating;

		review.likeCount = 0;
		review.commentCount = 0;
		review.createdAt = Instant.now();
		review.isDeleted = false;

		return review;
	}

	/* < 도메인 로직 > */

	public void updateContent(String newContent) {
		if (newContent != null && !newContent.equals(this.content)) {
			this.content = newContent;
			touch();
		}
	}

	public void updateRating(BigDecimal newRating) {
		if (this.rating != newRating) {
			this.rating = newRating;
			touch();
		}
	}

	public void increaseLikeCount() {
		this.likeCount++;
		touch();
	}

	public void decreaseLikeCount() {
		if (this.likeCount > 0) {
			this.likeCount--;
			touch();
		}
	}

	public void increaseCommentCount() {
		this.commentCount++;
		touch();
	}

	public void decreaseCommentCount() {
		if (this.commentCount > 0) {
			this.commentCount--;
			touch();
		}
	}

	public void delete() {
		if (!this.isDeleted) {
			this.isDeleted = true;
			touch();
		}
	}

	/* < 내부 공통 로직 - 수정시간 갱신 > */

	private void touch() {
		this.updatedAt = Instant.now();
	}
}
