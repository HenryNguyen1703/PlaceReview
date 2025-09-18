package vn.ngochieu.com.features.businesscategory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested BusinessCategory cannot be found.
 * <p>Typically thrown in service or repository layers when querying by ID or name
 * but no matching BusinessCategory exists.
 * <p>Maps to HTTP 404 (Not Found).
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {

    /**
     * Create exception with a custom message.
     */
    public CategoryNotFoundException(String message) {
        super(message);
    }

    /**
     * Create exception when a BusinessCategory with the given ID does not exist.
     */
    public CategoryNotFoundException(Long categoryId) {
        super("Business category not found with id: " + categoryId);
    }
}
