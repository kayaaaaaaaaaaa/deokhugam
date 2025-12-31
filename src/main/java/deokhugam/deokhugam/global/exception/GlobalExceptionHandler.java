package deokhugam.deokhugam.global.exception;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	// 런타임 예외지만 비즈니스 예외는 아닌 경우
	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<ErrorResponse> handleRuntimeException(Exception exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

	// 모든 핸들러가 잡지 못하는 예외가 발생한 경우 (최종 안전망 역할의 핸들러)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exception) {
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
			// 500 Internal Server Error
			case INTERNAL_SERVER_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;

		};
	}
}
