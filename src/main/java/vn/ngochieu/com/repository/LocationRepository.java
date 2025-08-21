package vn.ngochieu.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.ngochieu.com.entity.Locations;

public interface LocationRepository extends JpaRepository<Locations, Long> {
}
