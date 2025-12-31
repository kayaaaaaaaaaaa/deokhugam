package deokhugam.deokhugam.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deokhugam.deokhugam.auth.dto.response.LoginResponse;
import deokhugam.deokhugam.user.entity.User;
import deokhugam.deokhugam.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder; // BCrypt만 써도 충분

	@Transactional(readOnly = true)
	public LoginResponse login(String email, String rawPassword) {

		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new LoginFailedException());

		if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
			throw new LoginFailedException();
		}

		return LoginResponse.of(user);
	}
}
