package vn.ngochieu.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.ngochieu.com.entity.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}
