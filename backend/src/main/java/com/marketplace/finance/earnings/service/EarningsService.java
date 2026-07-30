package com.marketplace.finance.earnings.service;

import com.marketplace.finance.balance.service.BalanceService;
import com.marketplace.finance.earnings.dto.response.EarningsSummaryResponse;
import com.marketplace.finance.ledger.entity.LedgerEntry;
import com.marketplace.finance.ledger.enums.LedgerEntryType;
import com.marketplace.finance.ledger.service.query.LedgerQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EarningsService {

    private final LedgerQueryService ledgerQueryService;
    private final BalanceService balanceService;

    public EarningsSummaryResponse getCurrentProviderSummary() {

        List<LedgerEntry> entries = ledgerQueryService.getCurrentProviderLedgerEntries();

        BigDecimal grossIncome = sum(entries, LedgerEntryType.INCOME);

        BigDecimal totalCommission = sum(entries, LedgerEntryType.COMMISSION);

        BigDecimal totalRefunds = sum(entries, LedgerEntryType.REFUND);

        BigDecimal currentBalance = balanceService.calculateCurrentProviderBalance();

        long completedPayments = entries.stream()
                .filter(entry ->
                    entry.getEntryType() == LedgerEntryType.INCOME)
                .count();

        return EarningsSummaryResponse.builder()
            .grossIncome(grossIncome)
            .totalCommission(totalCommission)
            .totalRefunds(totalRefunds)
            .currentBalance(currentBalance)
            .completedPayments(completedPayments)
            .build();

    }

    private BigDecimal sum(List<LedgerEntry> entries, LedgerEntryType type) {

        return entries.stream()
            .filter(entry -> entry.getEntryType() == type)
            .map(LedgerEntry::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

}
