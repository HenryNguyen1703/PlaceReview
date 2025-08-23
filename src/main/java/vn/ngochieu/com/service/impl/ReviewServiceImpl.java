package vn.ngochieu.com.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import vn.ngochieu.com.entity.Locations;
import vn.ngochieu.com.entity.Reviews;
import vn.ngochieu.com.entity.Users;
import vn.ngochieu.com.exception.LogicCustomException;
import vn.ngochieu.com.mapper.ReviewMapper;
import vn.ngochieu.com.payload.request.CreateReviewRequest;
import vn.ngochieu.com.payload.response.CreateReviewResponse;
import vn.ngochieu.com.repository.LocationRepository;
import vn.ngochieu.com.repository.ReviewRepository;
import vn.ngochieu.com.service.ReviewService;
import vn.ngochieu.com.user_management.repository.UserRepository;
import vn.ngochieu.com.util.SecurityUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository reviewRepository;

    UserRepository userRepository;

    LocationRepository locationRepository;

    ReviewMapper reviewMapper;

    @Override
    public CreateReviewResponse createReview(CreateReviewRequest createReviewRequest, HttpServletRequest requestHttp) {

        // Check Token
        Optional<Users> checker = userRepository.findByEmail(SecurityUtils.getCurrentUser(requestHttp));
        if(checker.isEmpty()) {
            LogicCustomException logicCustomException = new LogicCustomException();
            logicCustomException.setMessage("Token is invalid or expired");
            logicCustomException.setCode(401);
            throw logicCustomException;
        }

        // Check user is CUSTOMER
        Users user = checker.get();
        if(user.getRole().equals("CUSTOMER")){
            LogicCustomException logicCustomException = new LogicCustomException();
            logicCustomException.setMessage("User is not a customer");
            logicCustomException.setCode(403);
            throw logicCustomException;
        }

        // Check location id is valid
        Locations location = locationRepository.findById(createReviewRequest.getLocationId())
                .orElseThrow(() -> new RuntimeException("Location not found"));

        Reviews reviews = reviewMapper.toEntity(createReviewRequest);
        reviews.setUser(user);
        reviews.setLocation(location);
        Reviews saveReview = reviewRepository.save(reviews);
        CreateReviewResponse createReviewResponse = reviewMapper.toCreateReviewResponse(saveReview);
        return createReviewResponse;
    }
}
