package vn.ngochieu.com.features.businesscategory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ngochieu.com.features.businesscategory.entity.BusinessCategory;

import java.util.Optional;


@Repository
public interface BusinessCategoryRepository extends JpaRepository<BusinessCategory, Long> {
    Boolean existsByCategoryCode(String categoryCode);
    Boolean existsByCategoryName(String categoryName);
}