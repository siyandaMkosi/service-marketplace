package com.marketplace.finance.ledger.service.query;

import com.marketplace.finance.ledger.dto.response.LedgerEntryResponse;
import com.marketplace.finance.ledger.mapper.LedgerMapper;
import com.marketplace.finance.ledger.repository.LedgerEntryRepository;
import com.marketplace.provider.entity.Provider;
import com.marketplace.provider.facade.ProviderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class  LedgerQueryService {

    private final LedgerEntryRepository ledgerEntryRepository;
    private final LedgerMapper ledgerMapper;
    private final ProviderFacade providerFacade;


    public List<LedgerEntryResponse> getCurrentProviderLedger() {

        Provider provider = providerFacade.findCurrentProvider();

        return ledgerEntryRepository
            .findAllByProviderOrderByOccurredAtDesc(provider)
            .stream()
            .map(ledgerMapper::toResponse)
            .toList();

    }

}
