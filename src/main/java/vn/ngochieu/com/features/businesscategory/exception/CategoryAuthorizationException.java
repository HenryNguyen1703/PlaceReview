package vn.ngochieu.com.features.businesscategory.exception;

import vn.ngochieu.com.common.exceptions.LogicCustomException;

/**
 * Exception thrown when user is not authorized for business category operations.
 */
public class CategoryAuthorizationException extends LogicCustomException {
    
    public CategoryAuthorizationException(String message, Integer code) {
        this.setMessage(message);
        this.setCode(code);
    }
    
    public static CategoryAuthorizationException tokenInvalid() {
        return new CategoryAuthorizationException("Token is invalid or expired", 401);
    }
    
    public static CategoryAuthorizationException notAdmin() {
        return new CategoryAuthorizationException("User is not admin", 403);
    }
}