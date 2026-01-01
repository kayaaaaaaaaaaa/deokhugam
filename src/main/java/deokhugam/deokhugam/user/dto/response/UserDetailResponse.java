package deokhugam.deokhugam.user.dto.response;

import java.time.Instant;
import java.util.UUID;

import deokhugam.deokhugam.user.entity.User;

public record UserDetailResponse(
	UUID id,
	String email,
	String nickname,
	Instant createdAt
) {

	public static UserDetailResponse of(User user) {
		return new UserDetailResponse(
			user.getId(),
			user.getEmail(),
			user.getNickname(),
			user.getCreatedAt()
		);
	}
}
