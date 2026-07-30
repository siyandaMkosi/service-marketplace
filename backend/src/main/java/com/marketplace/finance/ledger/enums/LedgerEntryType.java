package com.marketplace.finance.ledger.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LedgerEntryType {

    INCOME(LedgerDirection.CREDIT),

    COMMISSION(LedgerDirection.DEBIT),

    PAYOUT(LedgerDirection.DEBIT),

    REFUND(LedgerDirection.DEBIT),

    ADJUSTMENT(LedgerDirection.CREDIT);

    private final LedgerDirection direction;

}
