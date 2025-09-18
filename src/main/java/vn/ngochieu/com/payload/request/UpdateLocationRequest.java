package vn.ngochieu.com.payload.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateLocationRequest {
    
    @Size(min = 2, max = 100, message = "Location name must be between 2 and 100 characters")
    String name;
    
    @Size(min = 5, max = 200, message = "Address must be between 5 and 200 characters")
    String address;
    
    @Pattern(regexp = "^[0-9+\\-\\s()]{8,20}$", message = "Phone number format is invalid")
    String phone;
    
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    String description;
    
    @Size(min = 2, max = 20, message = "Category code must be between 2 and 20 characters")
    @Pattern(regexp = "^[A-Z0-9_]+$", message = "Category code must contain only uppercase letters, numbers, and underscores")
    String categoryCode;
}
