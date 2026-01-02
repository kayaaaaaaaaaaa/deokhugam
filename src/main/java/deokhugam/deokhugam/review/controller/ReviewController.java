package deokhugam.deokhugam.review.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import deokhugam.deokhugam.review.dto.request.ReviewCreateRequest;
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

}
