package com.marketplace.finance.ledger.controller;

import com.marketplace.common.response.ApiResponse;
import com.marketplace.common.response.ApiResponseBuilder;
import com.marketplace.finance.ledger.dto.response.LedgerEntryResponse;
import com.marketplace.finance.ledger.facade.LedgerFacade;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/finance/ledger")
public class LedgerController {

    private final LedgerFacade ledgerFacade;

    @GetMapping
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<ApiResponse<List<LedgerEntryResponse>>> getLedger(HttpServletRequest request) {

        List<LedgerEntryResponse> response = ledgerFacade.getCurrentProviderLedger();

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Ledger retrieved successfully.",
                response,
                request
            )
        );

    }

}
