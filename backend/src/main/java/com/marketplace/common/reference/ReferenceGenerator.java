package com.marketplace.common.reference;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReferenceGenerator {

    public String generatePaymentReference() {

        return "PAY-" +
            UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 16)
                .toUpperCase();

    }

    public String generateLedgerReference() {

        return "LEDGER-"
            + UUID.randomUUID()
            .toString()
            .replace("-", "")
            .substring(0, 16)
            .toUpperCase();

    }

    public String generatePayoutReference() {

        return "PO-"
            + UUID.randomUUID()
            .toString()
            .replace("-", "")
            .substring(0, 16)
            .toUpperCase();

    }
}
