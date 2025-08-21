package vn.ngochieu.com.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "LOCATIONS")
public class Locations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "ADDRESS", unique = true, nullable = false)
    private String address;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TYPE")
    private String type;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Users user;

}
