package vn.ngochieu.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.ngochieu.com.entity.Reviews;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Reviews, Long> {

    List<Reviews> findAllReviewsByLocationId(Long locationId);
}
