package vn.ngochieu.com.payload.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocationResponse {
    Long id;
    String name;
    String address;
    String phone;
    String description;
    String type;
    UserResponse user;
}
