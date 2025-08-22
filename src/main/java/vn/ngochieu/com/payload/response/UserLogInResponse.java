package vn.ngochieu.com.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogInResponse {
    private String username;
    private String email;
    private String token;
}
