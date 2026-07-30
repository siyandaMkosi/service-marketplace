package com.marketplace.finance.earnings.controller;

import com.marketplace.common.response.ApiResponse;
import com.marketplace.common.response.ApiResponseBuilder;
import com.marketplace.finance.earnings.dto.response.EarningsSummaryResponse;
import com.marketplace.finance.earnings.facade.EarningsFacade;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/finance/earnings")
public class EarningsController {

    private final EarningsFacade earningsFacade;

    @GetMapping("/summary")
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<ApiResponse<EarningsSummaryResponse>> getSummary(HttpServletRequest request) {

        EarningsSummaryResponse response = earningsFacade.getCurrentProviderSummary();

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Provider earnings summary retrieved successfully.",
                response,
                request
            )
        );

    }

}
