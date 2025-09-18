package vn.ngochieu.com.features.businesscategory.exception;

import vn.ngochieu.com.common.exceptions.LogicCustomException;
/**
 * Exception thrown when a BusinessCategory violates uniqueness or referential integrity rules.
 **/
 public class CategoryConflictException extends LogicCustomException {
    /**
     * Create exception with a custom message and HTTP 409 code.
     */

    public CategoryConflictException(String message) {
        this.setMessage(message);
        this.setCode(409);
    }
    
    public static CategoryConflictException codeExists(String code) {
        return new CategoryConflictException("Business category code already exists: " + code);
    }
    
    public static CategoryConflictException nameExists(String name) {
        return new CategoryConflictException("Business category name already exists: " + name);
    }
    
    public static CategoryConflictException inUseByLocations(Long categoryId) {
        return new CategoryConflictException("Business category is in use by locations and cannot be deleted: " + categoryId);
    }
}