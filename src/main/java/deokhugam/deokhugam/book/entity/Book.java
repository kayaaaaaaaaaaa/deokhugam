package deokhugam.deokhugam.book.entity;

import java.time.Instant;
import java.time.LocalDate;
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
@Table(
	name = "books",
	uniqueConstraints = {
		// ISBN은 전 세계적으로 유일한 식별자이므로 유니크 제약
		@UniqueConstraint(columnNames = "isbn")
	}
)
public class Book {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	private UUID id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "author", nullable = false)
	private String author;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "publisher", nullable = false)
	private String publisher;

	@Column(name = "published_date", nullable = false)
	private LocalDate publishedDate;

	@Column(name = "isbn", nullable = false, unique = true)
	private String isbn;

	@Column(name = "thumbnail_url", nullable = false)
	private String thumbnailUrl;

	@Column(name = "review_count", nullable = false)
	private int reviewCount = 0;

	@Column(name = "rating", nullable = false, precision = 2, scale = 1)
	private double rating;

	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@Column(name = "updated_at")
	private Instant updatedAt;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted = false;


	/* < 생성 로직 > */

	public static Book create(
		String title,
		String author,
		String description,
		String publisher,
		LocalDate publishedDate,
		String isbn,
		String thumbnailUrl,
		double rating
	) {
		Book book = new Book();

		book.id = UUID.randomUUID();
		book.title = title;
		book.author = author;
		book.description = description;
		book.publisher = publisher;
		book.publishedDate = publishedDate;
		book.isbn = isbn;
		book.thumbnailUrl = thumbnailUrl;
		book.rating = rating;
		book.reviewCount = 0;
		book.createdAt = Instant.now();
		book.isDeleted = false;

		return book;
	}

	/* < 도메인 메서드 > */

	public void increaseReviewCount() {
		this.reviewCount++;
		touch();
	}

	public void decreaseReviewCount() {
		if (this.reviewCount > 0) {
			this.reviewCount--;
			touch();
		}
	}

	public void updateRating(double newRating) {
		if (this.rating != newRating) {
			this.rating = newRating;
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

	/* < 집계 정보 > */


}
