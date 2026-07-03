package com.marketplace.provider.repository;

import com.marketplace.provider.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

    boolean existsByUserId(Long userId);

    Optional<Provider> findByUserId(Long userId);

}
