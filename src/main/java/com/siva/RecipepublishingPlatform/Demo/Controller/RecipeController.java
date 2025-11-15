package com.siva.RecipepublishingPlatform.Demo.Controller;

import com.siva.RecipepublishingPlatform.Demo.Entity.PagedResponse;
import com.siva.RecipepublishingPlatform.Demo.Entity.Recipes;
import com.siva.RecipepublishingPlatform.Demo.Service.RecipeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class RecipeController {

    @Autowired
    private RecipeManagementService recipeManagementService;

    @GetMapping("/recipes")
    public PagedResponse<Recipes> getRecipes(
            @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "published_from", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishedFrom,
            @RequestParam(value = "published_to", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishedTo,
            @RequestParam(value = "chef_id", required = false) Long chefId,
            @RequestParam(value = "chef_handle", required = false) String chefHandle,
            @RequestParam(value = "sortBy", defaultValue = "publishedDate") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "desc") String sortDir
    ) {
        final int maxPageSize = 50;
        if (pageSize > maxPageSize) pageSize = maxPageSize;
        if (pageSize <= 0) pageSize = 10;
        if (pageNumber < 0) pageNumber = 0;

        return recipeManagementService.searchRecipes(
                pageNumber, pageSize, q, publishedFrom, publishedTo, chefId, chefHandle, sortBy, sortDir
        );
    }
}
