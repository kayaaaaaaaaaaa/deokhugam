package deokhugam.deokhugam.review.service;

import java.util.UUID;

import deokhugam.deokhugam.review.dto.request.ReviewCreateRequest;
import deokhugam.deokhugam.review.dto.request.ReviewUpdateRequest;
import deokhugam.deokhugam.review.entity.Review;

public interface ReviewService {

	Review create(ReviewCreateRequest request);

	Review findById(UUID reviewId);
}
