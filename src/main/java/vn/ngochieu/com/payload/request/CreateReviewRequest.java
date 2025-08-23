package vn.ngochieu.com.payload.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateReviewRequest {

    @Min(value = 1, message = "Rating must be >= 1")
    @Max(value = 5, message = "Rating must be <= 5")
    Integer rating;

    @NotBlank(message = "Comment is required")
    String comment;

    @NotNull(message = "LocationId is required")
    Long locationId;
}
