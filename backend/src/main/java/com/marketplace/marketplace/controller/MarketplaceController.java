package com.marketplace.marketplace.controller;

import com.marketplace.common.response.ApiResponse;
import com.marketplace.common.response.ApiResponseBuilder;
import com.marketplace.marketplace.dto.response.MarketplaceServiceResponse;
import com.marketplace.marketplace.facade.MarketplaceFacade;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/marketplace")
@RequiredArgsConstructor
public class MarketplaceController {

    private final MarketplaceFacade marketplaceFacade;

    @GetMapping("/service-offerings")
    public ResponseEntity<ApiResponse<List<MarketplaceServiceResponse>>> getServices(HttpServletRequest request) {

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Services retrieved successfully.",
                marketplaceFacade.findAllServiceOfferings(),
                request
            )
        );

    }

    @GetMapping("/service-offerings/{id}")
    public ResponseEntity<ApiResponse<MarketplaceServiceResponse>> getServiceOffering(@PathVariable Long id, HttpServletRequest request) {

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Service offering retrieved successfully.",
                marketplaceFacade.findServiceOfferingById(id),
                request
            )
        );

    }

}
