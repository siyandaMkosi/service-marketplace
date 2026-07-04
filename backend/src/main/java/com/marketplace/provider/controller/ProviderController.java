package com.marketplace.provider.controller;

import com.marketplace.common.response.ApiResponse;
import com.marketplace.common.response.ApiResponseBuilder;
import com.marketplace.provider.dto.request.ProviderRegistrationRequest;
import com.marketplace.provider.dto.request.ProviderUpdateRequest;
import com.marketplace.provider.dto.response.ProviderResponse;
import com.marketplace.provider.facade.ProviderFacade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderFacade providerFacade;

    @PostMapping
    public ResponseEntity<ApiResponse<ProviderResponse>> becomeProvider(@Valid @RequestBody ProviderRegistrationRequest request, HttpServletRequest httpRequest) {

        ProviderResponse response = providerFacade.becomeProvider(request);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(
                ApiResponseBuilder.success(
                    "Provider profile created successfully.",
                    response,
                    httpRequest
                )
            );
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<ProviderResponse>> getMyProfile(HttpServletRequest request) {

        ProviderResponse response = providerFacade.getCurrentProvider();

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Provider profile retrieved successfully.",
                response,
                request
            )
        );
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<ProviderResponse>> updateProvider(@Valid @RequestBody ProviderUpdateRequest request, HttpServletRequest httpRequest) {
        ProviderResponse response = providerFacade.updateProvider(request);

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Provider profile updated successfully.",
                response,
                httpRequest
            )
        );
    }

}
