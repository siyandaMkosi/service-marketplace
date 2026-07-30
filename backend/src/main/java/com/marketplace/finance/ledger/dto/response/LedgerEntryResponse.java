package com.marketplace.finance.ledger.dto.response;

import com.marketplace.finance.ledger.enums.LedgerEntryType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class LedgerEntryResponse {

    private Long id;

    private BigDecimal amount;

    private LedgerEntryType entryType;

    private String reference;

    private String description;

    private LocalDateTime occurredAt;

    private Long bookingId;

    private String paymentReference;

}
