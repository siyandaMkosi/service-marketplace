package com.marketplace.finance.balance.controller;

import com.marketplace.common.response.ApiResponse;
import com.marketplace.common.response.ApiResponseBuilder;
import com.marketplace.finance.balance.dto.response.BalanceResponse;
import com.marketplace.finance.balance.facade.BalanceFacade;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/finance/balance")
public class BalanceController {

    private final BalanceFacade balanceFacade;

    @GetMapping
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<ApiResponse<BalanceResponse>> getBalance(HttpServletRequest request) {

        BalanceResponse response = balanceFacade.getCurrentProviderBalance();

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Current balance retrieved successfully.",
                response,
                request
            )
        );

    }

}
