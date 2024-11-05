package com.kshrd.kroya_api.controller;

import com.kshrd.kroya_api.enums.PaymentType;
import com.kshrd.kroya_api.enums.PurchaseStatusType;
import com.kshrd.kroya_api.payload.BaseResponse;
import com.kshrd.kroya_api.payload.Purchase.PurchaseRequest;
import com.kshrd.kroya_api.service.FoodSell.FoodSellService;
import com.kshrd.kroya_api.service.Purchase.PurchaseService;
import com.kshrd.kroya_api.service.Receipt.ReceiptService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/purchase")
@AllArgsConstructor
@Slf4j
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final ReceiptService receiptService;
    private final FoodSellService foodSellService;

    @PostMapping
    public BaseResponse<?> addPurchase(@RequestBody PurchaseRequest purchaseRequest, @RequestParam PaymentType paymentType) {
        return purchaseService.addPurchase(purchaseRequest, paymentType);
    }

    @GetMapping("/orders/seller/{foodSellId}")
    public BaseResponse<?> getAllOrdersForSeller(@PathVariable Long foodSellId) {
        return purchaseService.getAllOrdersForSeller(foodSellId);
    }

    @GetMapping("/orders/buyer")
    public BaseResponse<?> getBuyerOrders() {
        return purchaseService.getOrdersForBuyer();
    }

    @PutMapping("/orders/{purchaseId}/status")
    public BaseResponse<?> updateOrderStatus(
            @PathVariable Long purchaseId,
            @RequestParam PurchaseStatusType newStatus) {
        return purchaseService.updateOrderStatus(purchaseId, newStatus);
    }

    @GetMapping("/seller/items")
    public BaseResponse<?> getSellerItemsWithOrderCounts() {
        return purchaseService.getSellerItemsWithOrderCounts();
    }

    @GetMapping("/all")
    public BaseResponse<?> getAllOrdersAndSales() {
        return purchaseService.getAllOrdersAndSales();
    }

    @GetMapping("/receipt/{purchaseId}")
    public BaseResponse<?> getReceiptByPurchaseId(@PathVariable Long purchaseId) {
        return receiptService.getReceiptByPurchaseId(purchaseId);
    }

    @GetMapping("/search")
    public BaseResponse<?> searchFoodSellInPurchaseByName(@RequestParam String name) {
        return purchaseService.searchFoodSellInPurchaseByName(name);
    }

}
