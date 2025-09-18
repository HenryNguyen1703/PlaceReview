package vn.ngochieu.com.features.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.ngochieu.com.features.review.entity.Reviews;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Reviews, Long> {

    List<Reviews> findAllReviewsByLocationId(Long locationId);
}
