package vn.ngochieu.com.common.payloads.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * Base API response wrapper for all endpoints.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseApiResponse<T> {
    private T data;
    private String message;
    private Integer status;
}