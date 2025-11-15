package com.siva.RecipepublishingPlatform.Demo.Repository;

import com.siva.RecipepublishingPlatform.Demo.Entity.Recipes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface RecipeRepository extends JpaRepository<Recipes, Long> {

    @Query("""
        SELECT r FROM Recipes r
        WHERE (:keyword IS NULL
               OR LOWER(r.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(r.summary) LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(r.ingredients) LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(r.steps) LIKE LOWER(CONCAT('%', :keyword, '%'))
        )
        AND (:chefId IS NULL OR r.chef.id = :chefId)
        AND (:chefHandle IS NULL OR LOWER(r.chef.handle) = LOWER(:chefHandle))
        AND (:publishedFrom IS NULL OR r.publishedDate >= :publishedFrom)
        AND (:publishedTo IS NULL OR r.publishedDate <= :publishedTo)
        """)
    Page<Recipes> findByKeywordAndFilters(
            @Param("keyword") String keyword,
            @Param("publishedFrom") LocalDate publishedFrom,
            @Param("publishedTo") LocalDate publishedTo,
            @Param("chefId") Long chefId,
            @Param("chefHandle") String chefHandle,
            Pageable pageable
    );
}
