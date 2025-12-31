package deokhugam.deokhugam.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import deokhugam.deokhugam.auth.dto.request.LoginRequest;
import deokhugam.deokhugam.auth.dto.request.SignUpRequest;
import deokhugam.deokhugam.auth.dto.response.LoginResponse;
import deokhugam.deokhugam.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
		LoginResponse response = authService.login(request.email(), request.password());

		return ResponseEntity.ok(response);
	}

	@PostMapping("/signup")
	public ResponseEntity<LoginResponse> signUp(@RequestBody @Valid SignUpRequest request) {
		LoginResponse response = authService.signUp(
			request.email(),
			request.nickname(),
			request.password()
		);

		return ResponseEntity.ok(response);
	}
}
