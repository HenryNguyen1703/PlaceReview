package vn.ngochieu.com.features.user_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.ngochieu.com.features.user_management.entity.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}
