package com.danyelbarboza.volatility_monitor.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.danyelbarboza.volatility_monitor.entity.FinancialAssetVolatility;

@RepositoryRestResource(path = "finav")
public interface FinancialAssetVolatilityRepository extends JpaRepository<FinancialAssetVolatility, Long> {
    
}
