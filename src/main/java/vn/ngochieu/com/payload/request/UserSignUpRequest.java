package vn.ngochieu.com.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.ngochieu.com.entity.Users;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSignUpRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    String email;

    @NotBlank(message = "Password is required")
    String password;

    @NotBlank(message = "Username is required")
    String username;

    @NotNull(message = "Role of user is required")
    Users.Role role;
}
