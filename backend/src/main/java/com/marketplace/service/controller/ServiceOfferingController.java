package com.marketplace.service.controller;

import com.marketplace.common.response.ApiResponse;
import com.marketplace.common.response.ApiResponseBuilder;
import com.marketplace.service.dto.request.ServiceOfferingCreateRequest;
import com.marketplace.service.dto.request.ServiceOfferingUpdateRequest;
import com.marketplace.service.dto.response.ServiceOfferingResponse;
import com.marketplace.service.facade.ServiceOfferingFacade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provider/services")
@RequiredArgsConstructor
public class ServiceOfferingController {

    private final ServiceOfferingFacade facade;

    @PostMapping
    public ResponseEntity<ApiResponse<ServiceOfferingResponse>> create(@Valid @RequestBody ServiceOfferingCreateRequest request, HttpServletRequest servletRequest) {

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Service created successfully.",
                facade.create(request),
                servletRequest
            )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ServiceOfferingResponse>>> getAll(HttpServletRequest request) {

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Services retrieved successfully.",
                facade.getAll(),
                request
            )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ServiceOfferingResponse>> getById(@PathVariable Long id, HttpServletRequest request) {

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Service retrieved successfully.",
                facade.getById(id),
                request
            )
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ServiceOfferingResponse>> update(@PathVariable Long id, @RequestBody ServiceOfferingUpdateRequest request, HttpServletRequest servletRequest) {

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Service updated successfully.",
                facade.update(id, request),
                servletRequest
            )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deactivate(
        @PathVariable Long id,
        HttpServletRequest request
    ) {

        facade.deactivate(id);

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Service deactivated successfully.",
                null,
                request
            )
        );
    }

}
