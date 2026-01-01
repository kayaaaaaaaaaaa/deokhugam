package deokhugam.deokhugam.book.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import deokhugam.deokhugam.book.entity.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookCreateRequest(
	@NotBlank
	String title,
	@NotBlank
	String author,
	@NotBlank
	String description,
	@NotBlank
	String publisher,
	@NotNull
	LocalDate publishedDate,
	@NotBlank
	String isbn,
	String thumbnailUrl
) {
	public Book toEntity() {
		String resolvedThumbnailUrl = (thumbnailUrl == null || thumbnailUrl.isBlank())
			? ""
			: thumbnailUrl;
		return Book.create(
			title,
			author,
			description,
			publisher,
			publishedDate,
			isbn,
			resolvedThumbnailUrl,
			BigDecimal.valueOf(0)
		);
	}
}
