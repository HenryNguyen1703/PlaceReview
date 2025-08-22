package vn.ngochieu.com.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.ngochieu.com.entity.Users;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Username is required")
    private String username;

    @NotNull(message = "Role of user is required")
    private Users.Role role;
}
