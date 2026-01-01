package deokhugam.deokhugam.book.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import deokhugam.deokhugam.book.dto.request.BookCreateRequest;
import deokhugam.deokhugam.book.dto.response.BookDetailResponse;
import deokhugam.deokhugam.book.entity.Book;
import deokhugam.deokhugam.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/books")
@RestController
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<BookDetailResponse> create(@Valid @ModelAttribute BookCreateRequest request) {
		Book book = bookService.create(request);
		BookDetailResponse response = BookDetailResponse.of(book);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(response);
	}

	@GetMapping("/{bookId}")
	public ResponseEntity<BookDetailResponse> detail(@PathVariable UUID bookId) {
		Book book = bookService.findById(bookId);
		BookDetailResponse response = BookDetailResponse.of(book);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}
	//
	// @PatchMapping("/{bookId}")
	// public ResponseEntity<BookDetailResponse> update(
	// 	@PathVariable UUID bookId,
	// 	@Valid @RequestBody BookUpdateRequest request
	// ) {
	// 	Book book = bookService.update(bookId, request.nickname());
	// 	BookDetailResponse response = BookDetailResponse.of(book);
	// 	return ResponseEntity
	// 		.status(HttpStatus.OK)
	// 		.body(response);
	// }
	//
	// @DeleteMapping("/{bookId}")
	// public ResponseEntity<Void> softDelete(
	// 	@PathVariable UUID bookId
	// ) {
	// 	bookService.softDelete(bookId);
	// 	return ResponseEntity
	// 		.status(HttpStatus.NO_CONTENT)
	// 		.build();
	// }
	//
	// @DeleteMapping("/{bookId}/hard")
	// public ResponseEntity<Void> hardDelete(
	// 	@PathVariable UUID bookId
	// ) {
	// 	bookService.hardDelete(bookId);
	// 	return ResponseEntity
	// 		.status(HttpStatus.NO_CONTENT)
	// 		.build();
	// }

}
