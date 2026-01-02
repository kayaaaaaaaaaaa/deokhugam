package deokhugam.deokhugam.review.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import deokhugam.deokhugam.review.dto.request.ReviewCreateRequest;
import deokhugam.deokhugam.review.dto.request.ReviewUpdateRequest;
import deokhugam.deokhugam.review.dto.response.ReviewDetailResponse;
import deokhugam.deokhugam.review.entity.Review;
import deokhugam.deokhugam.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/reviews")
@RestController
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;

	//Todo 리뷰 목록조회 기능 추가
	// @GetMapping(path = "")
	// public ResponseEntity<> list() {
	//
	// }

	@PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ReviewDetailResponse> create(
		@Valid @RequestBody ReviewCreateRequest request
	) {
		Review review = reviewService.create(request);
		ReviewDetailResponse response = ReviewDetailResponse.of(review);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(response);
	}

	@GetMapping("/{reviewId}")
	public ResponseEntity<ReviewDetailResponse> detail(@PathVariable UUID reviewId) {
		Review review = reviewService.findById(reviewId);
		ReviewDetailResponse response = ReviewDetailResponse.of(review);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}

	@PatchMapping("/{reviewId}")
	public ResponseEntity<ReviewDetailResponse> update(
		@PathVariable UUID reviewId,
		@RequestHeader("deokhugam-request-user-id") UUID loginUserId,
		@Valid @RequestBody ReviewUpdateRequest request
	) {
		Review review = reviewService.update(reviewId, request, loginUserId);
		ReviewDetailResponse response = ReviewDetailResponse.of(review);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<Void> softDelete(
		@PathVariable UUID reviewId,
		@RequestHeader("deokhugam-request-user-id") UUID loginUserId

	) {
		reviewService.softDelete(reviewId, loginUserId);
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}

	@DeleteMapping("/{reviewId}/hard")
	public ResponseEntity<Void> hardDelete(
		@PathVariable UUID reviewId,
		@RequestHeader("deokhugam-request-user-id") UUID loginUserId
	) {
		reviewService.hardDelete(reviewId, loginUserId);
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}

}
