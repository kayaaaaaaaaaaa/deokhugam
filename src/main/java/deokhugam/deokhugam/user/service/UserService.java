package deokhugam.deokhugam.user.service;

import java.util.UUID;

import deokhugam.deokhugam.user.entity.User;

public interface UserService {
	User findUserById(UUID userId);

	User findUserByEmail(String email);

	User update(UUID userId, String nickname);

	void softDelete(UUID userId);

	void hardDelete(UUID userId);
}
