package deokhugam.deokhugam.book.service;

import java.util.UUID;

import deokhugam.deokhugam.book.dto.request.BookCreateRequest;
import deokhugam.deokhugam.book.entity.Book;
import deokhugam.deokhugam.user.entity.User;

public interface BookService {
	Book create(BookCreateRequest request);

	Book findById(UUID userId);

	User update(UUID userId, String nickname);

	void softDelete(UUID userId);

	void hardDelete(UUID userId);
}
