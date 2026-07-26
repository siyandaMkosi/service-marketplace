package com.marketplace.service.controller;

import com.marketplace.common.response.ApiResponse;
import com.marketplace.common.response.ApiResponseBuilder;
import com.marketplace.service.dto.response.ServiceCategoryResponse;
import com.marketplace.service.facade.ServiceCategoryFacade;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class ServiceCategoryController {

    private final ServiceCategoryFacade facade;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ServiceCategoryResponse>>> getAll(HttpServletRequest request) {

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Categories retrieved successfully.",
                facade.getAll(),
                request
            )
        );

    }

}
