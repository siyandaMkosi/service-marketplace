package com.marketplace.finance.earnings.facade;

import com.marketplace.finance.earnings.dto.response.EarningsSummaryResponse;
import com.marketplace.finance.earnings.service.EarningsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EarningsFacade {

    private final EarningsService earningsService;

    public EarningsSummaryResponse getCurrentProviderSummary() {

        return earningsService.getCurrentProviderSummary();

    }

}
