package com.marketplace.finance.balance.service;

import com.marketplace.finance.balance.dto.response.BalanceResponse;
import com.marketplace.finance.ledger.entity.LedgerEntry;
import com.marketplace.finance.ledger.enums.LedgerDirection;
import com.marketplace.finance.ledger.service.query.LedgerQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BalanceService {

    private final LedgerQueryService ledgerQueryService;

    public BigDecimal calculateCurrentProviderBalance() {

        List<LedgerEntry> entries = ledgerQueryService.getCurrentProviderLedgerEntries();

        BigDecimal balance = BigDecimal.ZERO;

        for (LedgerEntry entry : entries) {

            if (entry.getEntryType().getDirection() == LedgerDirection.CREDIT) {

                balance = balance.add(entry.getAmount());

            } else {

                balance = balance.subtract(entry.getAmount());

            }

        }

        return balance;

    }
    public BalanceResponse getCurrentProviderBalance() {

        return BalanceResponse.builder()
            .currentBalance(calculateCurrentProviderBalance())
            .build();

    }

}
