package vn.ngochieu.com.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.ngochieu.com.payload.request.CreateLocationRequest;
import vn.ngochieu.com.service.LocationService;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
@Validated
@Tag(name = "Location APIs", description = "APIs for location")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "*")
public class LocationController {

    LocationService locationService;

    @PostMapping
    @Operation(summary = "Create a new location", description = "API for role BUSINESS to create a new location")
    public ResponseEntity<?> createLocation(@RequestBody @Valid CreateLocationRequest createLocationRequest, HttpServletRequest requestHttp) {
         return locationService.createLocation(createLocationRequest, requestHttp);
    }

    @GetMapping
    @Operation(summary = "List of locations", description = "API for GUESS and CUSTOMER to see list of locations")
    public ResponseEntity<?> listLocations() {
        return locationService.listLocations();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Location detail", description = "API for GUESS and CUSTOMER to see location detail by id")
    public ResponseEntity<?> detailLocation(@PathVariable Long id) {
        return locationService.detailLocation(id);
    }
}
