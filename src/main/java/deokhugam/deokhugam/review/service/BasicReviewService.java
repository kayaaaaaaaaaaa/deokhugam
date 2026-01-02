package deokhugam.deokhugam.review.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deokhugam.deokhugam.book.entity.Book;
import deokhugam.deokhugam.book.exception.BookNotFoundException;
import deokhugam.deokhugam.book.repository.BookRepository;
import deokhugam.deokhugam.review.dto.request.ReviewCreateRequest;
import deokhugam.deokhugam.review.entity.Review;
import deokhugam.deokhugam.review.repository.ReviewRepository;
import deokhugam.deokhugam.user.entity.User;
import deokhugam.deokhugam.user.exception.UserNotFoundException;
import deokhugam.deokhugam.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasicReviewService implements ReviewService {

	private final ReviewRepository reviewRepository;
	private final BookRepository bookRepository;
	private final UserRepository userRepository;

	@Override
	@Transactional
	public Review create(ReviewCreateRequest request) {
		Book book = bookRepository.findById(request.bookId())
			.orElseThrow(() -> BookNotFoundException.withId(request.bookId()));
		User user = userRepository.findById(request.userId())
			.orElseThrow(() -> UserNotFoundException.withId(request.userId()));

		Review review = request.toEntity(user, book);
		return reviewRepository.save(review);
	}

}
