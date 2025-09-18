package vn.ngochieu.com.features.businesscategory.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import vn.ngochieu.com.features.businesscategory.entity.BusinessCategory;
import vn.ngochieu.com.features.businesscategory.repository.BusinessCategoryRepository;
import vn.ngochieu.com.features.businesscategory.mapper.CategoryMapper;
import vn.ngochieu.com.features.businesscategory.dto.CreateCategoryRequest;
import vn.ngochieu.com.features.businesscategory.dto.UpdateCategoryRequest;
import vn.ngochieu.com.features.businesscategory.dto.CategoryResponse;
import vn.ngochieu.com.features.businesscategory.exception.CategoryNotFoundException;
import vn.ngochieu.com.features.businesscategory.exception.CategoryConflictException;
import vn.ngochieu.com.features.businesscategory.exception.CategoryAuthorizationException;

import vn.ngochieu.com.features.businesscategory.service.CategoryService;
import vn.ngochieu.com.features.location.repository.LocationRepository;
import vn.ngochieu.com.features.user_management.entity.Users;
import vn.ngochieu.com.features.user_management.repository.UserRepository;
import vn.ngochieu.com.util.SecurityUtils;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing business categories.
 * Provides CRUD operations with authorization and validation logic.
 *
 * @author Dinh
 */

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {

    BusinessCategoryRepository businessCategoryRepository;
    UserRepository userRepository;
    CategoryMapper categoryMapper;
    LocationRepository locationRepository;

    // Ensure requester is authenticated and has ADMIN role

    private Users requireAdmin(HttpServletRequest httpRequest) {
        Optional<Users> checker = userRepository.findByEmail(SecurityUtils.getCurrentUser(httpRequest));
        if (checker.isEmpty()) {
            throw CategoryAuthorizationException.tokenInvalid();
        }
        Users user = checker.get();
        if (user.getRole() != Users.Role.ADMIN) {
            throw CategoryAuthorizationException.notAdmin();
        }
        return user;
    }

    //Create business category with uniqueness checks for code and name.
    @Override
    public CategoryResponse createCategory(CreateCategoryRequest request, HttpServletRequest httpRequest) {
        requireAdmin(httpRequest);
        if (businessCategoryRepository.existsByCategoryCode(request.getCode())) {
            throw CategoryConflictException.codeExists(request.getCode());
        }
        if (businessCategoryRepository.existsByCategoryName(request.getName())) {
            throw CategoryConflictException.nameExists(request.getName());
        }
        BusinessCategory entity = categoryMapper.toEntity(request);
        BusinessCategory saved = businessCategoryRepository.save(entity);
        return categoryMapper.toDto(saved);
    }

    // Update business category; re-check conflicts when changing code or name.
    @Override
    public CategoryResponse updateCategory(Long categoryId, UpdateCategoryRequest request, HttpServletRequest httpRequest) {
        requireAdmin(httpRequest);
        BusinessCategory entity = businessCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        if (request.getCode() != null && !request.getCode().equals(entity.getCategoryCode())) {
            if (businessCategoryRepository.existsByCategoryCode(request.getCode())) {
                throw CategoryConflictException.codeExists(request.getCode());
            }
        }
        if (request.getName() != null && !request.getName().equals(entity.getCategoryName())) {
            if (businessCategoryRepository.existsByCategoryName(request.getName())) {
                throw CategoryConflictException.nameExists(request.getName());
            }
        }

        categoryMapper.update(entity, request);
        BusinessCategory saved = businessCategoryRepository.save(entity);
        return categoryMapper.toDto(saved);
    }

    // List all business categories.
    @Override
    public List<CategoryResponse> listCategories() {
        return categoryMapper.toDtoList(businessCategoryRepository.findAll());
    }

    //Get business category detail by id (404 if not found).
    @Override
    public CategoryResponse detailCategory(Long categoryId) {
        BusinessCategory entity = businessCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        return categoryMapper.toDto(entity);
    }

    //Delete business category if not referenced by any location; 404 if missing, 409 if in use.
    @Override
    public void deleteCategory(Long categoryId, HttpServletRequest httpRequest) {
        requireAdmin(httpRequest);
        boolean hasLocations = false;
        try {
            hasLocations = locationRepository.existsByCategoryCategoryId(categoryId);
        } catch (Exception ignored) {}

        if (hasLocations) {
            throw CategoryConflictException.inUseByLocations(categoryId);
        }

        if (!businessCategoryRepository.existsById(categoryId)) {
            throw new CategoryNotFoundException(categoryId);
        }
        try {
            businessCategoryRepository.deleteById(categoryId);
        } catch (DataIntegrityViolationException e) {
            throw CategoryConflictException.inUseByLocations(categoryId);
        }
    }
}