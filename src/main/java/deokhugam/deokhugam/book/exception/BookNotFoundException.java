package deokhugam.deokhugam.book.exception;

import java.util.UUID;

import deokhugam.deokhugam.global.exception.ErrorCode;

public class BookNotFoundException extends BookException {

	public BookNotFoundException() {
		super(ErrorCode.BOOK_NOT_FOUND);
	}

	public static BookNotFoundException withId(UUID id) {
		BookNotFoundException exception = new BookNotFoundException();
		exception.addDetail("id", id);
		return exception;
	}
}
