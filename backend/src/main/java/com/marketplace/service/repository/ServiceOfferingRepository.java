package com.marketplace.service.repository;

import com.marketplace.provider.entity.Provider;
import com.marketplace.service.entity.ServiceCategory;
import com.marketplace.service.entity.ServiceOffering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceOfferingRepository
    extends JpaRepository<ServiceOffering, Long> {

    List<ServiceOffering> findAllByProvider(Provider provider);

    Optional<ServiceOffering> findByIdAndProvider(Long id, Provider provider);

    boolean existsByProviderAndName(Provider provider, String name);

    List<ServiceOffering> findAllByProviderAndActiveTrue(Provider provider);

    List<ServiceOffering> findAllByActiveTrueOrderByNameAsc();

}
