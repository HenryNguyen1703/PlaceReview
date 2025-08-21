package vn.ngochieu.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.ngochieu.com.entity.Reviews;

public interface ReviewRepository extends JpaRepository<Reviews, Long> {
}
