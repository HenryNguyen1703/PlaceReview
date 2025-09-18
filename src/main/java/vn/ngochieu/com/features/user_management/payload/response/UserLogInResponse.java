package vn.ngochieu.com.features.user_management.payload.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLogInResponse {
    String username;
    String email;
    String token;
}
