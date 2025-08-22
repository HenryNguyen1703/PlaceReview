package vn.ngochieu.com.payload.request;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLocationRequest {
    private String name;
    private String address;
    private String phone;
    private String description;
    private String type;
}
