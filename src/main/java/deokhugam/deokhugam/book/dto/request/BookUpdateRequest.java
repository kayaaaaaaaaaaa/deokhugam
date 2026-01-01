package deokhugam.deokhugam.book.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookUpdateRequest(
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
	String thumbnailUrl
) {
}
