package vn.ngochieu.com.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import vn.ngochieu.com.entity.Locations;
import vn.ngochieu.com.payload.request.CreateLocationRequest;

import java.util.List;

public interface LocationService {
    Locations createLocation(CreateLocationRequest createLocationRequest, HttpServletRequest requestHttp);

    List<Locations> listLocations();

    Locations detailLocation(Long locationId);
}
