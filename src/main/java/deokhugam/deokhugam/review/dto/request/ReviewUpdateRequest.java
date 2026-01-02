package deokhugam.deokhugam.review.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;

public record ReviewUpdateRequest(
	@Size(max = 500)
	String content,

	@DecimalMin("1.0")
	@DecimalMax("5.0")
	@Digits(integer = 1, fraction = 1)
	BigDecimal rating
) {
}
