package com.kshrd.kroya_api.service.Bank;

import com.kshrd.kroya_api.dto.UserDTO;
import com.kshrd.kroya_api.entity.BankEntity;
import com.kshrd.kroya_api.entity.UserEntity;
import com.kshrd.kroya_api.enums.CurrencyType;
import com.kshrd.kroya_api.payload.Bank.BankRequest;
import com.kshrd.kroya_api.payload.BaseResponse;
import com.kshrd.kroya_api.repository.Bank.BankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    // Create a new bank account
    @Override
    public BaseResponse<?> addBank(BankRequest bankRequest, CurrencyType currencyType) {

        // Get the currently authenticated user
        UserEntity currentUser = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = currentUser.getId();
        log.info("User authenticated: {}", currentUser.getEmail());

        // Check if the user already has a bank account (optional validation)
        Optional<BankEntity> existingBank = bankRepository.findByUserId(userId);
        if (existingBank.isPresent()) {
            return BaseResponse.builder()
                    .message("User already has a bank account")
                    .statusCode(String.valueOf(HttpStatus.CONFLICT.value()))
                    .build();
        }

        // Map BankRequest to BankEntity and set user
        BankEntity bankEntity = BankEntity.builder()
                .accountNumber(bankRequest.getAccountNumber())
                .accountName(bankRequest.getAccountName())
                .bankLogo(bankRequest.getBankLogo())
                .bankName(bankRequest.getBankName())
                .currencyType(currencyType)
                .user(currentUser)
                .build();

        // Save the new bank entity
        bankRepository.save(bankEntity);
        log.info("Bank account created successfully for user ID: {}", userId);

        // Return a success response
        return BaseResponse.builder()
                .message("Bank account created successfully")
                .statusCode(String.valueOf(HttpStatus.CREATED.value()))
                .payload(bankEntity)
                .build();
    }

    // Update an existing bank account
    @Override
    public BaseResponse<?> updateBank(Long bankId, BankRequest bankRequest, CurrencyType currencyType) {

        // Get the currently authenticated user
        UserEntity currentUser = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("User authenticated: {}", currentUser.getEmail());

        // Fetch the bank entity by ID
        Optional<BankEntity> bankEntityOptional = bankRepository.findById(bankId);
        if (bankEntityOptional.isEmpty()) {
            return BaseResponse.builder()
                    .message("Bank account not found")
                    .statusCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                    .build();
        }

        BankEntity bankEntity = bankEntityOptional.get();

        // Check if the bank account belongs to the current user
        if (!bankEntity.getUser().getId().equals(currentUser.getId())) {
            return BaseResponse.builder()
                    .message("Unauthorized: You cannot update this bank account")
                    .statusCode(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
                    .build();
        }

        // Update the bank entity with the provided request details
        bankEntity.setAccountNumber(bankRequest.getAccountNumber());
        bankEntity.setAccountName(bankRequest.getAccountName());
        bankEntity.setBankLogo(bankRequest.getBankLogo());
        bankEntity.setBankName(bankRequest.getBankName());
        bankEntity.setCurrencyType(currencyType);

        // Save the updated entity
        bankRepository.save(bankEntity);
        log.info("Bank account updated successfully with ID: {}", bankId);

        return BaseResponse.builder()
                .message("Bank account updated successfully")
                .statusCode(String.valueOf(HttpStatus.OK.value()))
                .payload(bankEntity)
                .build();
    }

    // Delete a bank account
    @Override
    public BaseResponse<?> deleteBank(Long bankId) {

        // Get the currently authenticated user
        UserEntity currentUser = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("User authenticated: {}", currentUser.getEmail());

        Optional<BankEntity> bankEntityOptional = bankRepository.findById(bankId);
        if (bankEntityOptional.isPresent()) {
            BankEntity bankEntity = bankEntityOptional.get();

            // Check if the Bank Account belongs to the current user
            if (!bankEntity.getUser().getId().equals(currentUser.getId())) {
                return BaseResponse.builder()
                        .message("Unauthorized: You cannot delete this bank account")
                        .statusCode(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
                        .build();
            }

            bankRepository.deleteById(bankId);
            log.info("Bank account deleted successfully with ID: {}", bankId);
            return BaseResponse.builder()
                    .message("Bank account deleted successfully")
                    .statusCode(String.valueOf(HttpStatus.OK.value()))
                    .build();
        } else {
            return BaseResponse.builder()
                    .message("Bank account not found")
                    .statusCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                    .build();
        }
    }

    // Get bank account by current user
    @Override
    public BaseResponse<?> getByCurrentUser() {
        // Get the currently authenticated user
        UserEntity currentUser = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Fetching bank account for the current user ID: {}", currentUser.getId());

        // Fetch the bank entity based on the current user's ID
        Optional<BankEntity> bankEntityOptional = bankRepository.findByUserId(currentUser.getId());

        if (bankEntityOptional.isEmpty()) {
            return BaseResponse.builder()
                    .message("Bank account not found for the current user")
                    .statusCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                    .build();
        }

        BankEntity bankEntity = bankEntityOptional.get();

        // Map UserEntity to UserDTO
        UserDTO userDTO = new UserDTO(
                bankEntity.getUser().getId(),
                bankEntity.getUser().getFullName(),
                bankEntity.getUser().getProfileImage()
        );

        // Build a response payload map including BankEntity and UserDTO
        Map<String, Object> payload = new HashMap<>();
        payload.put("bank", bankEntity);
        payload.put("user", userDTO);

        return BaseResponse.builder()
                .message("Bank account fetched successfully")
                .statusCode(String.valueOf(HttpStatus.OK.value()))
                .payload(payload)
                .build();
    }
}
