package vn.ngochieu.com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    enum Status{
        PENDING, APPROVED, REJECTED
    }
}
