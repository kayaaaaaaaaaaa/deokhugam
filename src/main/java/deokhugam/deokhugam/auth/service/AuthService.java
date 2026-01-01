package deokhugam.deokhugam.auth.service;

import deokhugam.deokhugam.user.entity.User;

public interface AuthService {
	User login(String email, String rawPassword);

	User signUp(String email, String nickname, String rawPassword);

}
