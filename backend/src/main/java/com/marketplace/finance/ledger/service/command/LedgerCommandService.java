package com.marketplace.finance.ledger.service.command;

import com.marketplace.finance.ledger.entity.LedgerEntry;
import com.marketplace.finance.ledger.enums.LedgerEntryType;
import com.marketplace.finance.ledger.factory.LedgerFactory;
import com.marketplace.finance.ledger.repository.LedgerEntryRepository;
import com.marketplace.finance.payout.entity.Payout;
import com.marketplace.payment.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class  LedgerCommandService {

    private final LedgerFactory ledgerFactory;
    private final LedgerEntryRepository ledgerEntryRepository;

    public void recordIncome(Payment payment) {

        validateIncomeCanBeRecorded(payment);

        LedgerEntry ledgerEntry = ledgerFactory.recordIncome(payment);

        ledgerEntryRepository.save(ledgerEntry);

    }

    public void recordPayout(Payout payout) {

        LedgerEntry ledgerEntry = ledgerFactory.createPayoutEntry(payout);

        ledgerEntryRepository.save(ledgerEntry);

    }

    private void validateIncomeCanBeRecorded(Payment payment) {

        if (ledgerEntryRepository.existsByPaymentAndEntryType(payment, LedgerEntryType.INCOME)) {

            throw new IllegalStateException(
                "Income has already been recorded for payment " + payment.getTransactionReference()
            );

        }

    }

}
