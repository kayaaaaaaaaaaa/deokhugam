package deokhugam.deokhugam.book.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import deokhugam.deokhugam.book.entity.Book;

public record BookDetailResponse(
	UUID id,
	String title,
	String author,
	String description,
	String publisher,
	LocalDate publishedDate,
	String isbn,
	String thumbnailUrl,
	int reviewCount,
	BigDecimal rating,
	Instant createdAt,
	Instant updatedAt
) {

	public static BookDetailResponse of(Book book) {
		return new BookDetailResponse(
			book.getId(),
			book.getTitle(),
			book.getAuthor(),
			book.getDescription(),
			book.getPublisher(),
			book.getPublishedDate(),
			book.getIsbn(),
			book.getThumbnailUrl(),
			book.getReviewCount(),
			book.getRating(),
			book.getCreatedAt(),
			book.getUpdatedAt()
		);
	}
}
