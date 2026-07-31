package com.marketplace.finance.payout.gateway;

import com.marketplace.finance.payout.gateway.dto.PayoutGatewayRequest;
import com.marketplace.finance.payout.gateway.dto.PayoutGatewayResponse;

public interface PayoutGateway {

    PayoutGatewayResponse transfer(PayoutGatewayRequest request);

}
