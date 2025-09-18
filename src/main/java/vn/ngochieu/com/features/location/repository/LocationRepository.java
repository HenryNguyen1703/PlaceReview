package vn.ngochieu.com.features.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ngochieu.com.features.location.entity.Locations;


@Repository
public interface LocationRepository extends JpaRepository<Locations, Long> {

    boolean existsByCategoryCategoryId(Long categoryId);
}