package vn.ngochieu.com.features.businesscategory.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * BusinessCategory entity for managing business location categories.
 * Represents business categories in the PlaceReview system.
 * Stores a unique code, name, and description for each business category.
 * Helps organize and classify business locations, making it easier to extend service logic and scale the platform.
 * Why created Business Category:
 * - To separate business classification from the Location entity.
 * - Examples: FOOD (restaurants), HOTEL (accommodations),....
 * @author Dinh
 */
@Entity
@Table(name = "BUSINESS_CATEGORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusinessCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long categoryId;

    @Column(name = "CATEGORYCODE", unique = true, nullable = false)
    String categoryCode;

    @Column(name = "CATEGORYNAME", unique = true, nullable = false)
    String categoryName;

    @Column(name = "DESCRIPTION")
    String description;
}