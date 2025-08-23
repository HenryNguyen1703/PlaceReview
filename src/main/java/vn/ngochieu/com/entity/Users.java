package vn.ngochieu.com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "EMAIL", unique = true, nullable = false)
    String email;

    @Column(name = "PASSWORD", nullable = false)
    String password;

    @Column(name = "USERNAME", nullable = false)
    String username;

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    Role role;

    public enum Role{
        ADMIN, CUSTOMER, BUSINESS
    }
}
