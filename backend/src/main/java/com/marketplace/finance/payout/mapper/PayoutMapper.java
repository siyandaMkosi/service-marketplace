package com.marketplace.finance.payout.mapper;

import com.marketplace.finance.payout.dto.response.PayoutResponse;
import com.marketplace.finance.payout.entity.Payout;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PayoutMapper {

    PayoutResponse toResponse(Payout payout);

}
