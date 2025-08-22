package vn.ngochieu.com.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import vn.ngochieu.com.payload.request.CreateLocationRequest;

public interface LocationService {
    ResponseEntity<?> createLocation(CreateLocationRequest createLocationRequest, HttpServletRequest requestHttp);

    ResponseEntity<?> listLocations();
}
