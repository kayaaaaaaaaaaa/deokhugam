package deokhugam.deokhugam.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	// global
	INTERNAL_SERVER_ERROR("서버 내부 오류가 발생했습니다."),
	INVALID_REQUEST("잘못된 요청입니다."),
	ILLEGAL_ARGUMENT_ERROR("잘못된 인수가 전달되었습니다."),

	// auth
	LOGIN_INPUT_INVALID("이메일 또는 비밀번호가 올바르지 않습니다."),
	EMAIL_ALREADY_EXISTS("이미 존재하는 이메일입니다."),
	NICKNAME_ALREADY_EXISTS("이미 존재하는 닉네임입니다."),

	// user
	USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
	USER_ACCESS_DENIED("접근 권한이 없습니다"),

	// book
	BOOK_NOT_FOUND("해당 도서를 찾을 수 없습니다."),

	// review
	REVIEW_NOT_FOUND("해당 리뷰를 찾을 수 없습니다."),
	REVIEW_ALREADY_EXISTS("해당 도서에 작성된 리뷰가 존재합니다."),
	REVIEW_ACCESS_DENIED("리뷰 접근 권한이 없습니다.");

	private final String message;
}
