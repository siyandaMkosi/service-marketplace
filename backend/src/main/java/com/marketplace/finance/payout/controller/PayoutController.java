package com.marketplace.finance.payout.controller;

import com.marketplace.finance.payout.dto.request.CreatePayoutRequest;
import com.marketplace.finance.payout.dto.request.RejectPayoutRequest;
import com.marketplace.finance.payout.dto.response.PayoutResponse;
import com.marketplace.finance.payout.facade.PayoutFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payouts")
@RequiredArgsConstructor
@Tag(name = "Payout", description = "Provider payout management")
public class PayoutController {

    private final PayoutFacade payoutFacade;

    @PostMapping
    @Operation(summary = "Request a payout")
    public ResponseEntity<PayoutResponse> requestPayout(@Valid @RequestBody CreatePayoutRequest request) {

        PayoutResponse response = payoutFacade.requestPayout(request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);

    }

    @GetMapping
    @Operation(summary = "Get current provider payout history")
    public ResponseEntity<List<PayoutResponse>> getCurrentProviderPayouts() {

        return ResponseEntity.ok(payoutFacade.getCurrentProviderPayouts());

    }

    @GetMapping("/{payoutId}")
    @Operation(summary = "Get payout by id")
    public ResponseEntity<PayoutResponse> getPayout(@PathVariable Long payoutId) {

        return ResponseEntity.ok(payoutFacade.getPayout(payoutId));

    }


}
