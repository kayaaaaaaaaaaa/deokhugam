package deokhugam.deokhugam.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
	@Email(message = "이메일 형식이 올바르지 않습니다.")
	@NotBlank(message = "이메일은 필수입니다.")
	String email,
	@NotBlank
	@Size(min = 2, max = 20, message = "닉네임은 2자 이상 20자 이하입니다.")
	@Pattern(
		regexp = "^[A-Za-z0-9가-힣_]+$",
		message = "닉네임은 영문, 숫자, 한글, 언더스코어(_)만 허용됩니다."
	)
	String nickname,
	@NotBlank
	@Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하입니다.")
	@Pattern(
		regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$",
		message = "비밀번호는 영문과 숫자를 포함해야 합니다."
	)
	String password
) {}
