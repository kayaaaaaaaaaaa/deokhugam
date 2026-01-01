package deokhugam.deokhugam.global.exception;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 덕후감 비즈니스 예외에 대한 처리를 합니다.
	 *
	 * @param exception
	 * @return 해당 exception에 대한 에러 응답
	 */

	@ExceptionHandler(DeokhugamException.class)
	protected ResponseEntity<ErrorResponse> handleDeokhugamException(DeokhugamException exception) {
		// 예시) 커스텀 예외 발생: code=USER_NOT_FOUND, message = 사용자 없음
		log.error("커스텀 예외 발생: code={}, message = {}", exception.getErrorCode(), exception.getMessage());
		HttpStatus status = detemineHttpStatus(exception);
		ErrorResponse errorResponse = new ErrorResponse(exception, status.value());
		return ResponseEntity.status(status).body(errorResponse);
	}



	/**
	 * 시스템 예외에 대한 처리를 합니다.
	 *
	 * @param exception
	 * @return 해당 exception에 대한 에러 응답
	 */

	// 요청 값 검증 실패 시
	// 예시) 요청 값 검증 실패: message=..., errors=[field=email, rejected=bad, msg=이메일 형식이 아닙니다 | field=age, rejected=-1, msg=최소 18 이상이어야 합니다]
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		String formattedErrors = fieldErrors.stream()
			.map(error -> String.format("field=%s, rejected=%s, msg=%s",
				error.getField(),
				String.valueOf(error.getRejectedValue()),
				error.getDefaultMessage()))
			.collect(Collectors.joining(" | "));
		log.error("요청 값 검증 실패: message={}, errors=[{}]", exception.getMessage(), formattedErrors);
		ErrorResponse errorResponse = new ErrorResponse(fieldErrors, HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	// 런타임 예외지만 비즈니스 예외는 아닌 경우
	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<ErrorResponse> handleRuntimeException(Exception exception) {
		// 예시) 런타임 예외 발생 (스택트레이스 포함)
		log.error("런타임 예외 발생", exception);
		ErrorResponse errorResponse = new ErrorResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

	// 모든 핸들러가 잡지 못하는 예외가 발생한 경우 (최종 안전망 역할의 핸들러)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exception) {
		// 예시) 예상하지 못한 예외 발생 (스택트레이스 포함)
		log.error("예상하지 못한 예외 발생", exception);
		ErrorResponse errorResponse = new ErrorResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

	/**
	 * 덕후감 비즈니스 예외에 대한 상태코드를 반환합니다.
	 *
	 * @param exception
	 * @return 해당 exception에 대한 상태코드
	 */
	private HttpStatus detemineHttpStatus(DeokhugamException exception) {
		ErrorCode errorCode = exception.getErrorCode();

		return switch (errorCode) {

			// 400 Bad Request
			case ILLEGAL_ARGUMENT_ERROR,
				 INVALID_REQUEST -> HttpStatus.BAD_REQUEST;

			// 401 Unauthorized
			case LOGIN_INPUT_INVALID ->  HttpStatus.UNAUTHORIZED;

			// 404 Not Found
			case USER_NOT_FOUND -> HttpStatus.NOT_FOUND;

			// 409 Conflict
			case EMAIL_ALREADY_EXISTS,
				 NICKNAME_ALREADY_EXISTS -> HttpStatus.CONFLICT;

			// 500 Internal Server Error
			case INTERNAL_SERVER_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;

		};
	}
}
