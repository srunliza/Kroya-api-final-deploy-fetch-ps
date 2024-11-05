package com.kshrd.kroya_api.payload.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileUpdateRequest {
    private String profileImage;
    private String fullName;
    private String phoneNumber;
    private String location;
}