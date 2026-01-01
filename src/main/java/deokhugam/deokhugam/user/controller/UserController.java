package deokhugam.deokhugam.user.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import deokhugam.deokhugam.user.dto.request.UserUpdateRequest;
import deokhugam.deokhugam.user.dto.response.UserDetailResponse;
import deokhugam.deokhugam.user.entity.User;
import deokhugam.deokhugam.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/{userId}")
	public ResponseEntity<UserDetailResponse> detail(@PathVariable UUID userId) {
		User user = userService.findUserById(userId);
		UserDetailResponse response = UserDetailResponse.of(user);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}

	@PatchMapping("/{userId}")
	public ResponseEntity<UserDetailResponse> update(
		@PathVariable UUID userId,
		@Valid @RequestBody UserUpdateRequest request
	) {
		User user = userService.update(userId, request.nickname());
		UserDetailResponse response = UserDetailResponse.of(user);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> softDelete(
		@PathVariable UUID userId
	) {
		userService.softDelete(userId);
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}

}
