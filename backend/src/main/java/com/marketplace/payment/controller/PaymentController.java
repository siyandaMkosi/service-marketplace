package com.marketplace.payment.controller;

import com.marketplace.common.response.ApiResponse;
import com.marketplace.common.response.ApiResponseBuilder;
import com.marketplace.payment.dto.request.PaymentRequest;
import com.marketplace.payment.dto.response.PaymentResponse;
import com.marketplace.payment.facade.PaymentFacade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentFacade paymentFacade;

    @PostMapping("/booking/{bookingId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ApiResponse<PaymentResponse>> createPayment(@PathVariable Long bookingId, @Valid @RequestBody PaymentRequest request, HttpServletRequest httpRequest) {

        PaymentResponse response = paymentFacade.createPayment(bookingId, request);

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Payment initiated successfully.",
                response,
                httpRequest
            )
        );

    }

    @PostMapping("/{transactionReference}/verify")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ApiResponse<PaymentResponse>> verifyPayment(@PathVariable String transactionReference, HttpServletRequest httpRequest) {

        PaymentResponse response = paymentFacade.verifyPayment(transactionReference);

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Payment verified successfully.",
                response,
                httpRequest
            )
        );

    }

    @GetMapping("/{transactionReference}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ApiResponse<PaymentResponse>> getPayment(@PathVariable String transactionReference, HttpServletRequest request) {

        PaymentResponse response = paymentFacade.getPayment(transactionReference);

        return ResponseEntity.ok(
            ApiResponseBuilder.success(
                "Payment retrieved successfully.",
                response,
                request
            )
        );

    }

}
