package deokhugam.deokhugam.user.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deokhugam.deokhugam.user.entity.User;
import deokhugam.deokhugam.user.exception.UserNotFoundException;
import deokhugam.deokhugam.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicUserService implements UserService {

	private final UserRepository userRepository;

	@Override
	public User findUserById(UUID userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> UserNotFoundException.withId(userId));
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> UserNotFoundException.withEmail(email));
		return user;
	}

