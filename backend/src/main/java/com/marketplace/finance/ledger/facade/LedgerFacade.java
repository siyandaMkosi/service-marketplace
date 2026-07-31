package com.marketplace.finance.ledger.facade;

import com.marketplace.finance.ledger.dto.response.LedgerEntryResponse;
import com.marketplace.finance.ledger.service.LedgerService;
import com.marketplace.finance.payout.entity.Payout;
import com.marketplace.payment.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LedgerFacade {

    private final LedgerService ledgerService;

    public void recordIncome(Payment payment) {

        ledgerService.recordIncome(payment);

    }

    public void recordPayout(Payout payout) {

        ledgerService.recordPayout(payout);

    }

    public List<LedgerEntryResponse> getCurrentProviderLedger() {

        return ledgerService.getCurrentProviderLedger();

    }

}
