package deokhugam.deokhugam.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
	@NotBlank
	@Size(min = 2, max = 20, message = "닉네임은 2자 이상 20자 이하입니다.")
	@Pattern(
		regexp = "^[A-Za-z0-9가-힣_]+$",
		message = "닉네임은 영문, 숫자, 한글, 언더스코어(_)만 허용됩니다."
	)
	String nickname
) {}
