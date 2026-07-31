package com.marketplace.finance.payout.controller;

import com.marketplace.finance.payout.dto.request.RejectPayoutRequest;
import com.marketplace.finance.payout.dto.response.PayoutResponse;
import com.marketplace.finance.payout.facade.PayoutFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/payouts")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin payout management")
public class AdminPayoutController {

    private final PayoutFacade payoutFacade;
    @PutMapping("/{payoutId}/approve")
    @Operation(summary = "Approve payout")
    public ResponseEntity<PayoutResponse> approvePayout(@PathVariable Long payoutId) {

        return ResponseEntity.ok(payoutFacade.approvePayout(payoutId));

    }

    @PutMapping("/{payoutId}/reject")
    @Operation(summary = "Reject payout")
    public ResponseEntity<PayoutResponse> rejectPayout(@PathVariable Long payoutId, @Valid @RequestBody RejectPayoutRequest request) {

        return ResponseEntity.ok(
            payoutFacade.rejectPayout(
                payoutId,
                request.getReason()
            )
        );

    }
}
