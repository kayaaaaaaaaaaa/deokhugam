package deokhugam.deokhugam.book.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deokhugam.deokhugam.book.dto.request.BookCreateRequest;
import deokhugam.deokhugam.book.entity.Book;
import deokhugam.deokhugam.book.repository.BookRepository;
import deokhugam.deokhugam.user.entity.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicBookService implements BookService {

	private final BookRepository bookRepository;

	@Override
	@Transactional
	public Book create(BookCreateRequest request) {
		Book book = request.toEntity();
		return bookRepository.save(book);
	}

	@Override
	public Book findById(UUID userId) {
		return null;
	}

	@Override
	public User update(UUID userId, String nickname) {
		return null;
	}

	@Override
	public void softDelete(UUID userId) {

	}

	@Override
	public void hardDelete(UUID userId) {

	}
}
