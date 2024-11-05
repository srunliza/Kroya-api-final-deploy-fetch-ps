package com.kshrd.kroya_api.controller;

import com.kshrd.kroya_api.payload.BaseResponse;
import com.kshrd.kroya_api.payload.FoodRecipe.FoodRecipeRequest;
import com.kshrd.kroya_api.service.FoodRecipe.FoodRecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/food-recipe")
@RequiredArgsConstructor
@Slf4j
public class FoodRecipeController {

    private final FoodRecipeService foodRecipeService;

    @PostMapping("/post-food-recipe")
    public BaseResponse<?> createRecipe(@RequestBody FoodRecipeRequest foodRecipeRequest) {
        return foodRecipeService.createRecipe(foodRecipeRequest);
    }

    @GetMapping("/list")
    public BaseResponse<?> getAllFoodRecipe() {
        return foodRecipeService.getAllFoodRecipes();
    }

    @PutMapping("/edit-food-recipe/{recipeId}")
    public BaseResponse<?> editRecipe(@PathVariable Long recipeId, @RequestBody FoodRecipeRequest foodRecipeRequest) {
        return foodRecipeService.editRecipe(recipeId, foodRecipeRequest);
    }

    @GetMapping("/cuisine/{cuisineId}")
    public BaseResponse<?> getFoodRecipeByCuisineID(@PathVariable Long cuisineId) {
        return foodRecipeService.getFoodRecipeByCuisineID(cuisineId);
    }
}
