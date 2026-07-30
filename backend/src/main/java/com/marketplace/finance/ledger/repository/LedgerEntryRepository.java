package com.marketplace.finance.ledger.repository;

import com.marketplace.finance.ledger.entity.LedgerEntry;
import com.marketplace.finance.ledger.enums.LedgerEntryType;
import com.marketplace.payment.entity.Payment;
import com.marketplace.provider.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LedgerEntryRepository extends JpaRepository<LedgerEntry, Long> {

    List<LedgerEntry> findAllByProviderOrderByOccurredAtDesc(Provider provider);

    List<LedgerEntry> findAllByPayment(Payment payment);

    List<LedgerEntry> findAllByProviderAndEntryTypeOrderByOccurredAtAsc(Provider provider, LedgerEntryType entryType);

    boolean existsByPaymentAndEntryType(Payment payment, LedgerEntryType entryType);

    List<LedgerEntry> findAllByProviderAndOccurredAtBetweenOrderByOccurredAtDesc(Provider provider, LocalDateTime start, LocalDateTime end);

}
