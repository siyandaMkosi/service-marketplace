package com.marketplace.finance.payout.repository;

import com.marketplace.finance.payout.entity.Payout;
import com.marketplace.finance.payout.enums.PayoutStatus;
import com.marketplace.provider.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PayoutRepository extends JpaRepository<Payout, Long> {

    boolean existsByProviderAndStatus(Provider provider, PayoutStatus status);

    List<Payout> findAllByProviderOrderByRequestedAtDesc(Provider provider);

    boolean existsByProviderAndStatusIn(Provider provider, Collection<PayoutStatus> statuses);

}
