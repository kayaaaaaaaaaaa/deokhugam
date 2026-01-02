package deokhugam.deokhugam.book.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import deokhugam.deokhugam.book.dto.request.BookCreateRequest;
import deokhugam.deokhugam.book.dto.request.BookUpdateRequest;
import deokhugam.deokhugam.book.entity.Book;
import deokhugam.deokhugam.book.exception.BookNotFoundException;
import deokhugam.deokhugam.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicBookService implements BookService {

	private final BookRepository bookRepository;

	@Override
	@Transactional
	public Book create(BookCreateRequest request, MultipartFile thumbnailImage) {
		// TODO 동일한 isbn으로 등록된 책 존재여부 검증로직 추가
		Book book = request.toEntity();
		return bookRepository.save(book);
	}

	@Override
	public Book findById(UUID bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
		return book;
	}

	@Override
	@Transactional
	public Book update(UUID bookId, BookUpdateRequest request) {
		Book book = bookRepository.findById(bookId)
			.orElseThrow(() -> BookNotFoundException.withId(bookId));
		book.updateDetails(
			request.title(),
			request.author(),
			request.description(),
			request.publisher(),
			request.publishedDate(),
			request.thumbnailUrl()
		);
		return book;
	}

	@Override
	@Transactional
	public void softDelete(UUID bookId) {
		Book book = bookRepository.findById(bookId)
			.orElseThrow(() -> BookNotFoundException.withId(bookId));
		book.softDelete();
	}

	// TODO 물리삭제 시 관련정보 모두 삭제
	@Override
	@Transactional
	public void hardDelete(UUID bookId) {
		Book book = bookRepository.findById(bookId)
			.orElseThrow(() -> BookNotFoundException.withId(bookId));
		bookRepository.delete(book);
	}
}
