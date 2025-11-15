package com.siva.RecipepublishingPlatform.Demo.Service;

import com.siva.RecipepublishingPlatform.Demo.Entity.PagedResponse;
import com.siva.RecipepublishingPlatform.Demo.Entity.Recipes;
import com.siva.RecipepublishingPlatform.Demo.Repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RecipeManagementService {

    @Autowired
    private RecipeRepository recipeRepository;

    public PagedResponse<Recipes> searchRecipes(int pageNumber, int pageSize,
                                                String q, LocalDate publishedFrom,
                                                LocalDate publishedTo, Long chefId,
                                                String chefHandle, String sortBy,
                                                String sortDir) {

        Sort.Direction direction = Sort.Direction.fromString(sortDir == null ? "DESC" : sortDir.toUpperCase());
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Recipes> recipesPage = recipeRepository.findByKeywordAndFilters(
                q, publishedFrom, publishedTo, chefId, chefHandle, pageable
        );

        PagedResponse<Recipes> pagedResponse = new PagedResponse<>();
        pagedResponse.setPageNumber(recipesPage.getNumber());
        pagedResponse.setPagesize(recipesPage.getSize());
        pagedResponse.setTotalElements(recipesPage.getTotalElements());
        pagedResponse.setTotalPages(recipesPage.getTotalPages());
        pagedResponse.setLast(recipesPage.isLast());
        pagedResponse.setFirst(recipesPage.isFirst());
        pagedResponse.setHasNext(recipesPage.hasNext());
        pagedResponse.setHasPrevious(recipesPage.hasPrevious());
        pagedResponse.setContent(recipesPage.getContent());
        return pagedResponse;
    }
}
