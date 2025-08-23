package vn.ngochieu.com.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vn.ngochieu.com.entity.Locations;
import vn.ngochieu.com.entity.Users;
import vn.ngochieu.com.payload.request.CreateLocationRequest;
import vn.ngochieu.com.repository.LocationRepository;
import vn.ngochieu.com.repository.UserRepository;
import vn.ngochieu.com.service.LocationService;
import vn.ngochieu.com.util.SecurityUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LocationServiceImpl implements LocationService {

    LocationRepository locationRepository;

    UserRepository userRepository;

    @Override
    public ResponseEntity<?> createLocation(CreateLocationRequest createLocationRequest, HttpServletRequest requestHttp) {

        // Find user by jwt
        Optional<Users> checker = userRepository.findByEmail(SecurityUtils.getCurrentUser(requestHttp));
        if(checker.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Token is invalid or expired");
        }

        // Create a new location
        Locations newLocation = new Locations();
        newLocation.setName(createLocationRequest.getName());
        newLocation.setAddress(createLocationRequest.getAddress());
        newLocation.setPhone(createLocationRequest.getPhone());
        newLocation.setDescription(createLocationRequest.getDescription());
        newLocation.setType(createLocationRequest.getType());
        newLocation.setUser(checker.get());

        locationRepository.save(newLocation);
        return ResponseEntity.ok(createLocationRequest);
    }

    @Override
    public ResponseEntity<?> listLocations() {
        List<Locations> locations = locationRepository.findAll();
        return ResponseEntity.ok(locations);
    }

    @Override
    public ResponseEntity<?> detailLocation(Long locationId) {
        Locations locations = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(locations);
    }
}
