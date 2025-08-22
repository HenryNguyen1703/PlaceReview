package vn.ngochieu.com.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomException> handleBindException(BindException e, HttpServletRequest request) {
        String errorMessage = "";
        if (e.getBindingResult().hasErrors()) {
            for (int i = 0; i < e.getBindingResult().getAllErrors().size(); i++) {
                errorMessage += e.getBindingResult().getAllErrors().get(i).getDefaultMessage();
                errorMessage += (i == e.getBindingResult().getAllErrors().size() - 1) ? "." : ", ";
            }
        }
        CustomException appException = new CustomException();
        appException.setMessage(errorMessage);
        appException.setStatus(400);
        appException.setTimestamp(new Date());
        appException.setPath(request.getRequestURI());
        return ResponseEntity.status(400).body(appException);
    }

    @ExceptionHandler(LogicCustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomException> handleLogicCustomException(LogicCustomException e, HttpServletRequest request) {
        CustomException appException = new CustomException();
        appException.setMessage(e.getMessage());
        appException.setStatus(e.getCode());
        appException.setTimestamp(new Date());
        appException.setPath(request.getRequestURI());
        return ResponseEntity.status(400).body(appException);
    }
}
