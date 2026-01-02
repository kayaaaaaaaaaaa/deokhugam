package deokhugam.deokhugam.review.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deokhugam.deokhugam.book.entity.Book;
import deokhugam.deokhugam.book.exception.BookNotFoundException;
import deokhugam.deokhugam.book.repository.BookRepository;
import deokhugam.deokhugam.review.dto.request.ReviewCreateRequest;
import deokhugam.deokhugam.review.dto.request.ReviewUpdateRequest;
import deokhugam.deokhugam.review.entity.Review;
import deokhugam.deokhugam.review.exception.ReviewAlreadyExistsException;
import deokhugam.deokhugam.review.exception.ReviewNotFoundException;
import deokhugam.deokhugam.review.exception.ReviewPermissionException;
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

	//Todo 리뷰 목록조회 기능 추가
	// @GetMapping(path = "")
	// public ResponseEntity<> list() {
	// }

	@Override
	@Transactional
	public Review create(ReviewCreateRequest request) {
		Book book = bookRepository.findById(request.bookId())
			.orElseThrow(() -> BookNotFoundException.withId(request.bookId()));
		User user = userRepository.findById(request.userId())
			.orElseThrow(() -> UserNotFoundException.withId(request.userId()));
		if (reviewRepository.existsByUserIdAndBookId(request.userId(), request.bookId())) {
			throw ReviewAlreadyExistsException.withUserAndBook(request.userId(), request.bookId());
		}

		Review review = request.toEntity(user, book);
		return reviewRepository.save(review);
	}

	@Override
	public Review findById(UUID reviewId) {
		return reviewRepository.findById(reviewId)
			.orElseThrow(() -> ReviewNotFoundException.withId(reviewId));
	}

	@Override
	@Transactional
	public Review update(UUID reviewId, ReviewUpdateRequest request, UUID loginUserId) {
		Review review = reviewRepository.findById(reviewId)
			.orElseThrow(() -> ReviewNotFoundException.withId(reviewId));
		if (!loginUserId.equals(review.getUser().getId())) {
			throw ReviewPermissionException.withUserAndBook(loginUserId, reviewId);
		}
		review.updateContent(request.content());
		review.updateRating(request.rating());
		return review;
	}

	@Override
	@Transactional
	public void softDelete(UUID reviewId, UUID loginUserId) {
		Review review = reviewRepository.findById(reviewId)
			.orElseThrow(() -> ReviewNotFoundException.withId(reviewId));
		if (!loginUserId.equals(review.getUser().getId())) {
			throw ReviewPermissionException.withUserAndBook(loginUserId, reviewId);
		}
		review.delete();
	}

	// TODO 물리삭제 시 관련정보 모두 삭제
	@Override
	@Transactional
	public void hardDelete(UUID reviewId, UUID loginUserId) {
		Review review = reviewRepository.findById(reviewId)
			.orElseThrow(() -> ReviewNotFoundException.withId(reviewId));
		if (!loginUserId.equals(review.getUser().getId())) {
			throw ReviewPermissionException.withUserAndBook(loginUserId, reviewId);
		}
		reviewRepository.delete(review);
	}

}
