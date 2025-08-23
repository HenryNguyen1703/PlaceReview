package vn.ngochieu.com.payload.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.ngochieu.com.entity.Locations;
import vn.ngochieu.com.entity.Reviews;
import vn.ngochieu.com.entity.Users;

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
