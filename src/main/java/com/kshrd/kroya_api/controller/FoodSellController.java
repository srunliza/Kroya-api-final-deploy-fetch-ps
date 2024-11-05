package com.kshrd.kroya_api.controller;

import com.kshrd.kroya_api.enums.CurrencyType;
import com.kshrd.kroya_api.payload.BaseResponse;
import com.kshrd.kroya_api.payload.FoodSell.FoodSellRequest;
import com.kshrd.kroya_api.service.FoodSell.FoodSellService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;

@RestController
@RequestMapping("api/v1/food-sell")
@RequiredArgsConstructor
@Slf4j
public class FoodSellController {

    private final FoodSellService foodSellService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/post-food-sell")
    public BaseResponse<?> createFoodSell(@RequestBody FoodSellRequest foodRecipeRequest,
                                          @RequestParam Long foodRecipeId,
                                          @RequestParam CurrencyType currencyType) {
        return foodSellService.createFoodSell(foodRecipeRequest, foodRecipeId, currencyType);
    }

    @GetMapping("/list")
    public BaseResponse<?> getAllFoodSells() {
        return foodSellService.getAllFoodSells();
    }

    @PutMapping("/edit-food-sell/{foodSellId}")
    public BaseResponse<?> editFoodSell(@PathVariable Long foodSellId, @RequestBody FoodSellRequest foodSellRequest) {
        return foodSellService.editFoodSell(foodSellId, foodSellRequest);
    }

    @GetMapping("/cuisine/{cuisineId}")
    public BaseResponse<?> getFoodSellByCuisineID(@PathVariable Long cuisineId) {
        return foodSellService.getFoodSellByCuisineID(cuisineId);
    }

}