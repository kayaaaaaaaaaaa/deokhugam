package deokhugam.deokhugam.user.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import deokhugam.deokhugam.user.dto.response.UserDetailResponse;
import deokhugam.deokhugam.user.entity.User;
import deokhugam.deokhugam.user.exception.UserNotFoundException;
import deokhugam.deokhugam.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserRepository userRepository;

	@GetMapping("/{userId}")
	public ResponseEntity<UserDetailResponse> detail(@PathVariable UUID userId){
		User user = userRepository.findById(userId)
			.orElseThrow(() -> UserNotFoundException.withId(userId));
		UserDetailResponse response = UserDetailResponse.of(user);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}

}
