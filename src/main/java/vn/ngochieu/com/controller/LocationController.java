package vn.ngochieu.com.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@CrossOrigin(origins = "*")
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<?> createLocation(@RequestBody @Valid CreateLocationRequest createLocationRequest, HttpServletRequest requestHttp) {
         return locationService.createLocation(createLocationRequest, requestHttp);
    }
}
