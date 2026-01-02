package deokhugam.deokhugam.review.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

import deokhugam.deokhugam.book.entity.Book;
import deokhugam.deokhugam.review.entity.Review;
import deokhugam.deokhugam.user.entity.User;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReviewCreateRequest(

	@NotNull
	UUID bookId,

	@NotNull
	UUID userId,

	@NotBlank
	@Size(max = 500) // 최대 500자
	String content,

	@NotNull
	@DecimalMin("1.0") // 별점 1~5 부여 가능
	@DecimalMax("5.0")
	@Digits(integer = 1, fraction = 1) // 소수점 앞뒤로 한 자리씩 허용
	BigDecimal rating

) {
	public Review toEntity(User user, Book book) {
		return Review.create(
			user,
			book,
			content,
			rating
		);
	}
}
