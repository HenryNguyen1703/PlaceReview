package vn.ngochieu.com.service;

import jakarta.servlet.http.HttpServletRequest;
import vn.ngochieu.com.payload.request.CreateReviewRequest;
import vn.ngochieu.com.payload.response.CreateReviewResponse;

public interface ReviewService {

    CreateReviewResponse createReview(CreateReviewRequest createReviewRequest, HttpServletRequest requestHttp);
}
