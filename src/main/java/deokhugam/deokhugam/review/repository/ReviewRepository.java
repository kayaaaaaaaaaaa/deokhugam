package deokhugam.deokhugam.review.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deokhugam.deokhugam.review.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
	boolean existsByUserIdAndBookId(UUID userId, UUID bookId);
}
