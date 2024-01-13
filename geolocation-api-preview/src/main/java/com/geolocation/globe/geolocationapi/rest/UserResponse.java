package com.geolocation.globe.geolocationapi.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    UUID userId;

    String message;

}