package com.kshrd.kroya_api.controller;


import com.kshrd.kroya_api.enums.ItemType;
import com.kshrd.kroya_api.payload.BaseResponse;
import com.kshrd.kroya_api.service.GuestUser.GuestUserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/guest-user")
@RequiredArgsConstructor
@Slf4j
public class GuestController {

    private final GuestUserService guestUserService;

    @Operation(
            summary = "🍲 Get All Food Sells",
            description = "Fetches a list of all food sells available to guest users."
    )
    @GetMapping("/food-sell/list")
    public BaseResponse<?> getAllFoodSells() {
        return guestUserService.getAllFoodSells();
    }

    @Operation(
            summary = "📖 Get All Food Recipes",
            description = "Retrieves a list of all food recipes available to guest users."
    )
    @GetMapping("/food-recipe/list")
    public BaseResponse<?> getAllFoodRecipe() {
        return guestUserService.getAllFoodRecipes();
    }

    @Operation(
            summary = "📂 Get Foods by Category",
            description = "Retrieves all food items under a specific category based on the provided category ID."
    )
    @GetMapping("/foods/{categoryId}")
    public BaseResponse<?> getAllFoodsByCategory(@PathVariable Long categoryId) {
        return guestUserService.getAllFoodsByCategory(categoryId);
    }

    @Operation(
            summary = "🌟 Get Popular Foods",
            description = "Retrieves a list of the most popular food items."
    )
    @GetMapping("/foods/popular")
    public BaseResponse<?> getPopularFoods() {
        return guestUserService.getPopularFoods();
    }

    @Operation(
            summary = "🔍 Get Food Detail by ID",
            description = "Retrieves detailed information about a specific food item using its ID and type."
    )
    @GetMapping("/foods/detail/{id}")
    public BaseResponse<?> getFoodDetail(@PathVariable Long id, @RequestParam ItemType itemType) {
        return guestUserService.getFoodDetail(id, itemType);
    }

    @Operation(
            summary = "🔎 Search Foods by Name",
            description = "Allows users to search for foods by name and retrieves matching results."
    )
    @GetMapping("/foods/search")
    public BaseResponse<?> searchFoodsByName(@RequestParam String name) {
        return guestUserService.searchFoodsByName(name);
    }

    @Operation(
            summary = "🍴 Get Food Recipes by Cuisine ID",
            description = "Fetches food recipes associated with a specific cuisine based on the cuisine ID."
    )
    @GetMapping("/food-recipe/{cuisineId}")
    public BaseResponse<?> getFoodRecipeByCuisineID(@PathVariable Long cuisineId) {
        return guestUserService.getFoodRecipeByCuisineID(cuisineId);
    }

    @Operation(
            summary = "🍽️ Get Food Sells by Cuisine ID",
            description = "Retrieves food sells linked to a particular cuisine using the cuisine ID."
    )
    @GetMapping("/food-sell/{cuisineId}")
    public BaseResponse<?> getFoodSellByCuisineID(@PathVariable Long cuisineId) {
        return guestUserService.getFoodSellByCuisineID(cuisineId);
    }

    @Operation(
            summary = "📜 Get All Food Names",
            description = "Provides a list of all food names available in the system."
    )
    @GetMapping("/foods/food-name-all")
    public BaseResponse<?> getAllFoodName() {
        return guestUserService.getAllFoodName();
    }

    @Operation(
            summary = "📋 Get All Foods",
            description = "Fetches a comprehensive list of all foods available to guest users."
    )
    @GetMapping("/foods/list")
    public BaseResponse<?> getAllFoods() {
        return guestUserService.getAllFoods();
    }
}

