package vn.ngochieu.com.features.businesscategory.service;

import jakarta.servlet.http.HttpServletRequest;
import vn.ngochieu.com.features.businesscategory.dto.CategoryResponse;
import vn.ngochieu.com.features.businesscategory.dto.CreateCategoryRequest;
import vn.ngochieu.com.features.businesscategory.dto.UpdateCategoryRequest;

import java.util.List;


public interface CategoryService {

    /**
     * Create a new business category.
     *
     * @param request the create request payload
     * @param httpRequest the current HTTP request (used for authorization)
     * @return response object with category details
     */
    CategoryResponse createCategory(CreateCategoryRequest request, HttpServletRequest httpRequest);

    /**
     * Update an existing category by ID.
     *
     * @param categoryId ID of the category
     * @param request the update request payload
     * @param httpRequest the current HTTP request (used for authorization)
     * @return updated category response
     */
    CategoryResponse updateCategory(Long categoryId, UpdateCategoryRequest request, HttpServletRequest httpRequest);

    /**
     * Get all categories in the system.
     *
     * @return list of categories
     */
    List<CategoryResponse> listCategories();

    /**
     * Get details of a category by ID.
     *
     * @param categoryId ID of the category
     * @return category response
     */
    CategoryResponse detailCategory(Long categoryId);

    /**
     * Delete a category by ID.
     * @param categoryId ID of the category
     * @param httpRequest the current HTTP request (used for authorization)
     */
    void deleteCategory(Long categoryId, HttpServletRequest httpRequest);
}
