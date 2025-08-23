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
import vn.ngochieu.com.exception.LogicCustomException;
import vn.ngochieu.com.mapper.LocationMapper;
import vn.ngochieu.com.payload.request.CreateLocationRequest;
import vn.ngochieu.com.repository.LocationRepository;
import vn.ngochieu.com.user_management.repository.UserRepository;
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

    LocationMapper locationMapper;

    @Override
    public Locations createLocation(CreateLocationRequest createLocationRequest, HttpServletRequest requestHttp) {

        // Find user by jwt
        Optional<Users> checker = userRepository.findByEmail(SecurityUtils.getCurrentUser(requestHttp));
        if(checker.isEmpty()) {
            LogicCustomException logicCustomException = new LogicCustomException();
            logicCustomException.setMessage("Token is invalid or expired");
            logicCustomException.setCode(401);
            throw logicCustomException;
        }

        // Create a new location
        Locations newLocation = locationMapper.toEntity(createLocationRequest);
        newLocation.setUser(checker.get());

        locationRepository.save(newLocation);
        return newLocation;
    }

    @Override
    public List<Locations> listLocations() {
        List<Locations> locations = locationRepository.findAll();
        return locations;
    }

    @Override
    public Locations detailLocation(Long locationId) {
        Locations location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return location;
    }
}
