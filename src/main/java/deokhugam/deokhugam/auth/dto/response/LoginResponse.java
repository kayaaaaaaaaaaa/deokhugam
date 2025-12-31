package deokhugam.deokhugam.auth.dto.response;

import java.util.UUID;

import deokhugam.deokhugam.user.entity.User;

public record LoginResponse(
	UUID id,
	String email,
	String nickname
) {

	public static LoginResponse of(User user) {
		return new LoginResponse(
			user.getId(),
			user.getEmail(),
			user.getNickname()
		);
	}
}
