package vn.ngochieu.com.exception;

import lombok.Data;

@Data
public class LogicCustomException extends RuntimeException {
    private String message;
    private Integer code;
}
