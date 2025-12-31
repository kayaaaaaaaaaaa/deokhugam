package deokhugam.deokhugam.global.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class DeokhugamException extends RuntimeException {

	private final Instant timestamp;
	private final ErrorCode errorCode;
	private final Map<String, Object> details;

	public DeokhugamException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
		this.timestamp = Instant.now();
		this.details = new HashMap<>();
	}

	public DeokhugamException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		this.timestamp = Instant.now();
		this.details = new HashMap<>();
	}

	public void addDetail(String key, Object value) {
		this.details.put(key, value);
	}
}
