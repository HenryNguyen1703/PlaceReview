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
import vn.ngochieu.com.entity.Reviews;
import vn.ngochieu.com.payload.request.CreateReviewRequest;
import vn.ngochieu.com.payload.response.ApiResponse;
import vn.ngochieu.com.payload.response.CreateReviewResponse;
import vn.ngochieu.com.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Validated
@Tag(name = "Review APIs", description = "APIs for review")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "*")
public class ReviewController {

    ReviewService reviewService;

    @PostMapping
    @Operation(summary = "Create a new review", description = "API for role CUSTOMER to create a new review")
    public ResponseEntity<?> createReview(@RequestBody @Valid CreateReviewRequest createReviewRequest, HttpServletRequest requestHttp) {
        ApiResponse<CreateReviewResponse> response = ApiResponse.<CreateReviewResponse>builder()
                .data(reviewService.createReview(createReviewRequest, requestHttp))
                .message("Create review successful")
                .status(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
