package com.marketplace.finance.ledger.service;

import com.marketplace.finance.ledger.dto.response.LedgerEntryResponse;
import com.marketplace.finance.ledger.service.command.LedgerCommandService;
import com.marketplace.finance.ledger.service.query.LedgerQueryService;
import com.marketplace.payment.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LedgerService {

    private final LedgerCommandService commandService;
    private final LedgerQueryService queryService;

    public void recordIncome(Payment payment) {

        commandService.recordIncome(payment);

    }

    public List<LedgerEntryResponse> getCurrentProviderLedger() {

        return queryService.getCurrentProviderLedger();

    }

}
