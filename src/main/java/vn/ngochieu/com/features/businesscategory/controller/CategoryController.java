package vn.ngochieu.com.features.businesscategory.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
import vn.ngochieu.com.common.payloads.response.BaseApiResponse;
import vn.ngochieu.com.features.businesscategory.dto.CreateCategoryRequest;
import vn.ngochieu.com.features.businesscategory.dto.UpdateCategoryRequest;
import vn.ngochieu.com.features.businesscategory.dto.CategoryResponse;
import vn.ngochieu.com.features.businesscategory.service.CategoryService;


import java.util.List;

/**
 * REST controller for managing business categories.
 * Provides endpoints managed for admin with creating, updating, retrieving, and deleting categories.
 * Base path: /api/business-categories
 * @author Dinh
 */
@RestController
@RequestMapping("/api/business-categories")
@RequiredArgsConstructor
@Validated
@Tag(name = "Business Category APIs", description = "APIs for managing business location categories")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "*")
public class CategoryController {

    CategoryService categoryService;

    @PostMapping
    @Operation(summary = "Create business category", description = "ADMIN creates a new business category")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(schema = @Schema(implementation = BaseApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "409", description = "Conflict")
    })
    public ResponseEntity<BaseApiResponse<CategoryResponse>> create(
            @RequestBody @Valid CreateCategoryRequest request, 
            HttpServletRequest httpRequest) {
        BaseApiResponse<CategoryResponse> response = BaseApiResponse.<CategoryResponse>builder()
                .data(categoryService.createCategory(request, httpRequest))
                .message("Create business category successful")
                .status(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update business category", description = "ADMIN updates a business category by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = BaseApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "409", description = "Conflict")
    })
    public ResponseEntity<BaseApiResponse<CategoryResponse>> update(
            @PathVariable("id") Long id, 
            @RequestBody @Valid UpdateCategoryRequest request, 
            HttpServletRequest httpRequest) {
        
        BaseApiResponse<CategoryResponse> response = BaseApiResponse.<CategoryResponse>builder()
                .data(categoryService.updateCategory(id, request, httpRequest))
                .message("Update business category successful")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    @Operation(summary = "List business categories", description = "List all business categories")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = BaseApiResponse.class)))
    })
    public ResponseEntity<BaseApiResponse<List<CategoryResponse>>> list() {
        BaseApiResponse<List<CategoryResponse>> response = BaseApiResponse.<List<CategoryResponse>>builder()
                .data(categoryService.listCategories())
                .message("List business categories successful")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Business category detail", description = "Get business category detail by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = BaseApiResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    public ResponseEntity<BaseApiResponse<CategoryResponse>> detail(@PathVariable("id") Long id) {
        BaseApiResponse<CategoryResponse> response = BaseApiResponse.<CategoryResponse>builder()
                .data(categoryService.detailCategory(id))
                .message("Business category detail successful")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete business category", description = "ADMIN deletes a business category by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = BaseApiResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "409", description = "Conflict")
    })
    public ResponseEntity<BaseApiResponse<Void>> delete(
            @PathVariable("id") Long id, 
            HttpServletRequest httpRequest) {
        
        categoryService.deleteCategory(id, httpRequest);
        BaseApiResponse<Void> response = BaseApiResponse.<Void>builder()
                .message("Delete business category successful")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}