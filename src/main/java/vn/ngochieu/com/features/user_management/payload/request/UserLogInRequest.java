package vn.ngochieu.com.features.user_management.payload.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLogInRequest {
    String email;
    String password;
}
