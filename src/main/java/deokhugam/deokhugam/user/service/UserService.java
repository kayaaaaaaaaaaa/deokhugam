package deokhugam.deokhugam.user.service;

import java.util.UUID;

import deokhugam.deokhugam.user.entity.User;

public interface UserService {
	User findById(UUID userId);

	User findByEmail(String email);

	User update(UUID pathUserId, UUID loginUserId, String nickname);

	void softDelete(UUID pathuserId, UUID loginUserId);

	void hardDelete(UUID pathuserId, UUID loginUserId);
}
