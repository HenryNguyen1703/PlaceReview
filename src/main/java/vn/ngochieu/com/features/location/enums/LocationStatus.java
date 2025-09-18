package vn.ngochieu.com.features.location.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;


@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum LocationStatus {
    ACTIVE("ACTIVE", "Location is active for store"),
    INACTIVE("INACTIVE", "Location is inactive for store"),
    DELETED("DELETED", "Location is deleted for store");
    
    final String value;
    final String description;

    public static LocationStatus fromValue(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Value cannot be null or blank");
        }
        return switch (value) {
            case "ACTIVE" -> ACTIVE;
            case "INACTIVE" -> INACTIVE;
            case "DELETED" -> DELETED;
            default -> throw new IllegalArgumentException("Invalid value: " + value);
        };
    }
    
    public String getValue() {
        return value;
    }
    
    public String getDescription() {
        return description;
    }
}