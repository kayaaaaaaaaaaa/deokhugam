package deokhugam.deokhugam.book.service;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import deokhugam.deokhugam.book.dto.request.BookCreateRequest;
import deokhugam.deokhugam.book.dto.request.BookUpdateRequest;
import deokhugam.deokhugam.book.entity.Book;

public interface BookService {
	Book create(BookCreateRequest request, MultipartFile thumbnailImage);

	Book findById(UUID bookId);

	Book update(UUID userId, BookUpdateRequest request);

	void softDelete(UUID bookId);

	void hardDelete(UUID bookId);
}
