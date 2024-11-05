package com.kshrd.kroya_api.controller;

import com.kshrd.kroya_api.entity.CredentialEntity;
import com.kshrd.kroya_api.payload.Auth.UserProfileUpdateRequest;
import com.kshrd.kroya_api.payload.BaseResponse;
import com.kshrd.kroya_api.service.User.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @Operation(summary = "🍽️ Retrieve Current User's Food Listings")
    @GetMapping("/foods")
    public BaseResponse<?> getFoodsByCurrentUser() {
        return userService.getFoodsByCurrentUser();
    }

    @Operation(summary = "🍽️ Retrieve Food Listings by User ID")
    @GetMapping("/foods/{userId}")
    public BaseResponse<?> getFoodsByUserId(@PathVariable Integer userId) {
        return userService.getFoodsByUserId(userId);
    }

    @Operation(summary = "✏️ Update User Profile")
    @PutMapping("/edit-profile")
    public BaseResponse<?> updateProfile(@RequestBody UserProfileUpdateRequest profileUpdateRequest) {
        return userService.updateProfile(profileUpdateRequest);
    }

    @Operation(summary = "🗑️ Delete User Account")
    @DeleteMapping("/delete-account")
    public BaseResponse<?> deleteAccount() {
        return userService.deleteAccount();
    }


    @Operation(summary = "🔗 Connect to Webill Service")
    @PostMapping("/connectWebill")
    public BaseResponse<?> connectWebill(@RequestBody CredentialEntity credentialEntity) {
        return userService.connectWebill(credentialEntity);
    }

    @Operation(summary = "🔌 Disconnect Webill Integration")
    @DeleteMapping("/disconnectWebill")
    public BaseResponse<?> disConnectWebill() {
        return userService.disconnectWebill();
    }

    @Operation(summary = "🔑 Retrieve User Credentials by User ID")
    @GetMapping("/credential/{userId}")
    public BaseResponse<?> getCredentialByUserId(@PathVariable("userId") Integer userId) {
        return userService.getCredentialByUserId(userId);
    }

    @Operation(summary = "📱 Retrieve Device Token by User ID")
    @GetMapping("/device-token/{userId}")
    public BaseResponse<?> getDeviceTokenByUserId(@PathVariable("userId") Integer userId) {
        return userService.getDeviceTokenByUserId(userId);
    }

    @Operation(summary = "📲 Register or Update Device Token")
    @PostMapping("/device-token")
    public BaseResponse<?> insertDeviceToken(@RequestParam String deviceToken) {
        return userService.insertDeviceToken(deviceToken);
    }

    @Operation(summary = "👤 Fetch User Profile Information")
    @GetMapping("/profile")
    public BaseResponse<?> getUserInfo() {
        return userService.getUserInfo();
    }

}
