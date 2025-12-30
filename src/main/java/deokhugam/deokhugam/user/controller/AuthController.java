package deokhugam.deokhugam.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import deokhugam.deokhugam.user.dto.request.LoginRequest;
import deokhugam.deokhugam.user.dto.response.LoginResponse;
import deokhugam.deokhugam.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(
		@RequestBody @Valid LoginRequest request
	) {
		LoginResponse response = authService.login(
			request.getEmail(),
			request.getPassword()
		);

		return ResponseEntity.ok(response);
	}
}
