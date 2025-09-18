package vn.ngochieu.com.features.review.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.ngochieu.com.features.location.entity.Locations;
import vn.ngochieu.com.features.user_management.entity.Users;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REVIEWS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "RATING")
    Integer rating;

    @Column(name = "COMMENT")
    String comment;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    Status status;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    Users user;

    @ManyToOne
    @JoinColumn(name = "LOCATION_ID")
    Locations location;

    public enum Status{
        PENDING, APPROVED, REJECTED
    }

    @PrePersist
    public void PrePersist(){
        this.status = Status.PENDING;
    }
}
