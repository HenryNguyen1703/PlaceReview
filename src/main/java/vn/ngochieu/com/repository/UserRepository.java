package vn.ngochieu.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.ngochieu.com.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
}
