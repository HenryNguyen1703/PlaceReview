package vn.ngochieu.com.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REVIEWS")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "RATING")
    public Integer rating;

    @Column(name = "COMMENT")
    public String comment;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    public Status status;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "LOCATION_ID")
    private Locations location;

    public enum Status{
        PENDING, APPROVED, REJECTED
    }
}
