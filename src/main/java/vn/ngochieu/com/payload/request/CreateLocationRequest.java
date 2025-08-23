package vn.ngochieu.com.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateLocationRequest {

    @NotBlank(message = "Name location is required")
    String name;

    @NotBlank(message = "Address is required")
    String address;

    @NotBlank(message = "Phone is required")
    String phone;

    String description;

    String type;
}
