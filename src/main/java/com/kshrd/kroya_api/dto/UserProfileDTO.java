package com.kshrd.kroya_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDTO {
    private Long userId;
    private String fullName;
    private String phoneNumber;
    @Builder.Default
    private String profileImage = "default.jpg";
    private String location;
}
