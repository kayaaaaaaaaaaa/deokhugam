package deokhugam.deokhugam.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deokhugam.deokhugam.auth.exception.DuplicateEmailException;
import deokhugam.deokhugam.auth.exception.DuplicateNicknameException;
import deokhugam.deokhugam.auth.exception.LoginFailedException;
import deokhugam.deokhugam.user.entity.User;
import deokhugam.deokhugam.user.exception.UserNotFoundException;
import deokhugam.deokhugam.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicAuthService implements AuthService {

	private final UserRepository userRepository;
	// 연습용 미니 프로젝트로, 시큐리티 없이 비밀번호 해시만 적용
	private final PasswordEncoder passwordEncoder;

	public User login(String email, String rawPassword) {

		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> UserNotFoundException.withEmail(email));

		if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
			throw new LoginFailedException();
		}

		return user;
	}

	@Transactional
	public User signUp(String email, String nickname, String rawPassword) {

		if (userRepository.findByEmail(email).isPresent()) {
			throw DuplicateEmailException.duplicateEmail(email);
		}
		if (userRepository.findByNickname(nickname).isPresent()) {
			throw DuplicateNicknameException.duplicateNickname(nickname);
		}

		String encodedPassword = passwordEncoder.encode(rawPassword);
		User user = User.create(email, nickname, encodedPassword);
		User savedUser = userRepository.save(user);

		return savedUser;
	}

}
