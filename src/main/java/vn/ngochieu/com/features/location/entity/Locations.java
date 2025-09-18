package vn.ngochieu.com.features.location.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.ngochieu.com.features.user_management.entity.Users;
import vn.ngochieu.com.features.businesscategory.entity.BusinessCategory;

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

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    Users user;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    BusinessCategory category;
}
