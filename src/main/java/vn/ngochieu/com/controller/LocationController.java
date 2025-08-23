package vn.ngochieu.com.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.ngochieu.com.entity.Locations;
import vn.ngochieu.com.payload.request.CreateLocationRequest;
import vn.ngochieu.com.payload.response.ApiResponse;
import vn.ngochieu.com.service.LocationService;

import java.util.List;

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
        ApiResponse<Locations> response = ApiResponse.<Locations>builder()
                .data(locationService.createLocation(createLocationRequest, requestHttp))
                .message("Creat location successful")
                .status(HttpStatus.CREATED.value())
                .build();
         return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "List of locations", description = "API for GUESS and CUSTOMER to see list of locations")
    public ResponseEntity<?> listLocations() {
        ApiResponse<List<Locations>> response = ApiResponse.<List<Locations>>builder()
                .data(locationService.listLocations())
                .message("List of locations")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Location detail", description = "API for GUESS and CUSTOMER to see location detail by id")
    public ResponseEntity<?> detailLocation(@PathVariable Long id) {
        ApiResponse<Locations> response = ApiResponse.<Locations>builder()
                .data(locationService.detailLocation(id))
                .message("Query location detail successful")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
