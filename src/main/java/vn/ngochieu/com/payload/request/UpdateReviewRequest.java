package vn.ngochieu.com.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.ngochieu.com.features.review.entity.Reviews;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateReviewRequest {

    @NotNull(message = "Status is required")
    Reviews.Status status;
}
