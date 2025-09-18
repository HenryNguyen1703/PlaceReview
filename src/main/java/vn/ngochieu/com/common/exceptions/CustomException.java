package vn.ngochieu.com.common.exceptions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomException {
    Date timestamp;
    Integer status;
    String message;
    String path;
}
