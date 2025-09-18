package vn.ngochieu.com.features.review.service;

import jakarta.servlet.http.HttpServletRequest;
import vn.ngochieu.com.payload.request.CreateReviewRequest;
import vn.ngochieu.com.payload.request.UpdateReviewRequest;
import vn.ngochieu.com.payload.response.CreateReviewResponse;

import java.util.List;

public interface ReviewService {

    CreateReviewResponse createReview(CreateReviewRequest createReviewRequest, HttpServletRequest requestHttp);

    List<CreateReviewResponse> listReview();

    void reviewModeration(Long id, UpdateReviewRequest updateReviewRequest, HttpServletRequest requestHttp);

    List<CreateReviewResponse> listLocationApprovedByLocationId(Long locationId);

    Double averageRatingByLocationId(Long locationId);

}
