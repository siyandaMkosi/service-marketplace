package com.marketplace.finance.payout.factory;

import com.marketplace.common.reference.ReferenceGenerator;
import com.marketplace.finance.payout.entity.Payout;
import com.marketplace.finance.payout.enums.PayoutStatus;
import com.marketplace.provider.entity.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PayoutFactory {

    private final ReferenceGenerator referenceGenerator;

    public Payout create(Provider provider, BigDecimal amount) {

        return Payout.builder()
            .provider(provider)
            .amount(amount)
            .payoutReference(referenceGenerator.generatePayoutReference())
            .status(PayoutStatus.PENDING)
            .requestedAt(LocalDateTime.now())
            .build();

    }

}
