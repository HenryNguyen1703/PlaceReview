package vn.ngochieu.com.features.businesscategory.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateCategoryRequest {
    
    @Size(min = 2, max = 20, message = "Category code must be between 2 and 20 characters")
    @Pattern(regexp = "^[A-Z0-9_]+$", message = "Category code must contain only uppercase letters, numbers, and underscores")
    String code;
    
    @Size(min = 2, max = 100, message = "Category name must be between 2 and 100 characters")
    String name;
    
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    String description;
}