package deokhugam.deokhugam.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import deokhugam.deokhugam.auth.dto.request.LoginRequest;
import deokhugam.deokhugam.auth.dto.request.SignUpRequest;
import deokhugam.deokhugam.auth.service.AuthService;
import deokhugam.deokhugam.user.dto.response.UserDetailResponse;
import deokhugam.deokhugam.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<UserDetailResponse> login(@RequestBody @Valid LoginRequest request) {
		User user = authService.login(request.email(), request.password());
		UserDetailResponse response = UserDetailResponse.of(user);

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}

	@PostMapping
	public ResponseEntity<UserDetailResponse> signUp(@RequestBody @Valid SignUpRequest request) {
		User user = authService.signUp(
			request.email(),
			request.nickname(),
			request.password()
		);
		UserDetailResponse response = UserDetailResponse.of(user);

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}
}
