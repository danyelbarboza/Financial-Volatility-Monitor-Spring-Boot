package com.danyelbarboza.volatility_monitor.service;

import org.springframework.stereotype.Service;

import com.danyelbarboza.volatility_monitor.dao.FinancialAssetVolatilityRepository;
import jakarta.persistence.EntityManager;
import java.util.List;


import com.danyelbarboza.volatility_monitor.entity.FinancialAssetVolatility;

@Service
public class FinancialAssetVolatilityService {
    private FinancialAssetVolatilityRepository financialAssetVolatilityRepository;
    private EntityManager entityManager;
    
    public FinancialAssetVolatilityService(FinancialAssetVolatilityRepository financialAssetVolatilityRepository, EntityManager entityManager) {
        this.financialAssetVolatilityRepository = financialAssetVolatilityRepository;
        this.entityManager = entityManager;
    }
    
    public List<FinancialAssetVolatility> getAllFinancialAssetVolatility() {
        return financialAssetVolatilityRepository.findAll();
    }
}
