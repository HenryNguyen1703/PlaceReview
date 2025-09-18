package vn.ngochieu.com.features.businesscategory.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    Long categoryId;
    String code;
    String name;
    String description;
}