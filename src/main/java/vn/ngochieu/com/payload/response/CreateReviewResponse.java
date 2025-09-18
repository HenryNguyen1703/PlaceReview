package vn.ngochieu.com.payload.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.ngochieu.com.features.review.entity.Reviews;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateReviewResponse {
    Long id;
    Integer rating;
    String comment;
    Reviews.Status status;
    LocationResponse location;
    UserResponse user;
}
