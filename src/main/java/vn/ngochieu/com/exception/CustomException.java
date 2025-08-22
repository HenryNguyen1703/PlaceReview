package vn.ngochieu.com.exception;

import lombok.Data;

import java.util.Date;

@Data
public class CustomException {
    private Date timestamp;
    private Integer status;
    private String message;
    private String path;
}
