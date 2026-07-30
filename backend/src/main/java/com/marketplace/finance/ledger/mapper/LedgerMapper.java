package com.marketplace.finance.ledger.mapper;

import com.marketplace.finance.ledger.dto.response.LedgerEntryResponse;
import com.marketplace.finance.ledger.entity.LedgerEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LedgerMapper {

    @Mapping(target = "bookingId", source = "booking.id")
    @Mapping(target = "paymentReference", source = "payment.transactionReference")
    LedgerEntryResponse toResponse(LedgerEntry ledgerEntry);

}
