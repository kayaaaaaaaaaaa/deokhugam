package deokhugam.deokhugam.user.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deokhugam.deokhugam.user.entity.User;
import deokhugam.deokhugam.user.exception.UserNotFoundException;
import deokhugam.deokhugam.user.exception.UserPermissionException;
import deokhugam.deokhugam.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicUserService implements UserService {

	private final UserRepository userRepository;

	@Override
	public User findById(UUID userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> UserNotFoundException.withId(userId));
		return user;
	}

	@Override
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> UserNotFoundException.withEmail(email));
		return user;
	}

	@Override
	@Transactional
	public User update(UUID pathUserId, UUID loginUserId, String nickname) {
		if (!pathUserId.equals(loginUserId)) {
			throw UserPermissionException.withPathUserIdAndLoginUserId(pathUserId, loginUserId);
		}
		User user = userRepository.findById(pathUserId)
			.orElseThrow(() -> UserNotFoundException.withId(pathUserId));
		user.updateProfile(nickname);
		return user;
	}

	@Override
	@Transactional
	public void softDelete(UUID pathUserId, UUID loginUserId) {
		if (!pathUserId.equals(loginUserId)) {
			throw UserPermissionException.withPathUserIdAndLoginUserId(pathUserId, loginUserId);
		}
		User user = userRepository.findById(pathUserId)
			.orElseThrow(() -> UserNotFoundException.withId(pathUserId));
		user.softDelete();
	}

	// TODO 물리삭제 시 관련정보 모두 삭제
	@Override
	@Transactional
	public void hardDelete(UUID pathUserId, UUID loginUserId) {
		if (!pathUserId.equals(loginUserId)) {
			throw UserPermissionException.withPathUserIdAndLoginUserId(pathUserId, loginUserId);
		}
		User user = userRepository.findById(pathUserId)
			.orElseThrow(() -> UserNotFoundException.withId(pathUserId));
		userRepository.delete(user);
	}
}
