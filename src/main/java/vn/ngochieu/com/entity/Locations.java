package vn.ngochieu.com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LOCATIONS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Locations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "NAME", unique = true, nullable = false)
    String name;

    @Column(name = "ADDRESS", unique = true, nullable = false)
    String address;

    @Column(name = "PHONE", nullable = false)
    String phone;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "TYPE")
    String type;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    Users user;

}
