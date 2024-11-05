package com.kshrd.kroya_api.controller;

import com.kshrd.kroya_api.enums.CurrencyType;
import com.kshrd.kroya_api.payload.Bank.BankRequest;
import com.kshrd.kroya_api.payload.BaseResponse;
import com.kshrd.kroya_api.service.Bank.BankService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/banks")
@RequiredArgsConstructor
@Slf4j
public class BankController {

    private final BankService bankService;

    // Create a new bank account
    @PostMapping
    public BaseResponse<?> addBank(@Valid @RequestBody
                                   BankRequest bankRequest,
                                   @RequestParam CurrencyType currencyType) {
        log.info("Creating a new bank account");
        return bankService.addBank(bankRequest, currencyType);
    }


    // Retrieve bank account by user ID
    @GetMapping
    public BaseResponse<?> getByCurrentUser() {
        return bankService.getByCurrentUser();
    }

    // Update a bank account
    @PutMapping("/{id}")
    public BaseResponse<?> updateBank(@PathVariable Long id,
                                      @RequestBody BankRequest bankRequest,
                                      @RequestParam CurrencyType currencyType) {
        log.info("Updating bank account with ID: {}", id);
        return bankService.updateBank(id, bankRequest, currencyType);
    }

    // Delete a bank account
    @DeleteMapping("/{id}")
    public BaseResponse<?> deleteBank(@PathVariable Long id) {
        log.info("Deleting bank account with ID: {}", id);
        return bankService.deleteBank(id);
    }
}

