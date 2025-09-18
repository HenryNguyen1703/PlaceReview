package vn.ngochieu.com.features.location.service;

import jakarta.servlet.http.HttpServletRequest;
import vn.ngochieu.com.payload.request.CreateLocationRequest;
import vn.ngochieu.com.payload.response.LocationResponse;

import java.util.List;

public interface LocationService {
    LocationResponse createLocation(CreateLocationRequest createLocationRequest, HttpServletRequest requestHttp);

    List<LocationResponse> listLocations();

    LocationResponse detailLocation(Long locationId);
}
