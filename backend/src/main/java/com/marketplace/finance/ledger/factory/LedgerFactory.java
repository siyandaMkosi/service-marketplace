package com.marketplace.finance.ledger.factory;

import com.marketplace.common.reference.ReferenceGenerator;
import com.marketplace.finance.ledger.entity.LedgerEntry;
import com.marketplace.finance.ledger.enums.LedgerEntryType;
import com.marketplace.payment.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class  LedgerFactory {

    private final ReferenceGenerator referenceGenerator;

    public LedgerEntry recordIncome(Payment payment) {

        return LedgerEntry.builder()
            .provider(payment.getProvider())
            .payment(payment)
            .booking(payment.getBooking())
            .amount(payment.getAmount())
            .entryType(LedgerEntryType.INCOME)
            .reference(referenceGenerator.generateLedgerReference())
            .description(buildIncomeDescription(payment))
            .occurredAt(LocalDateTime.now())
            .build();

    }

    private String buildIncomeDescription(Payment payment) {

        return "Income received for booking " + payment.getBooking().getId();

    }



}
