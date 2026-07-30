package com.marketplace.finance.balance.facade;

import com.marketplace.finance.balance.dto.response.BalanceResponse;
import com.marketplace.finance.balance.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BalanceFacade {

    private final BalanceService balanceService;

    public BalanceResponse getCurrentProviderBalance() {

        return balanceService.getCurrentProviderBalance();

    }

}
