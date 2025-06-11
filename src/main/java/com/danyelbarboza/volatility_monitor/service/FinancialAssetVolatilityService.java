package com.danyelbarboza.volatility_monitor.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danyelbarboza.volatility_monitor.client.YahooQuoteClient;
import com.danyelbarboza.volatility_monitor.dao.FinancialAssetVolatilityRepository;
import com.danyelbarboza.volatility_monitor.entity.FinancialAssetVolatility;
import com.danyelbarboza.volatility_monitor.entity.StocksToMonitor;

import jakarta.persistence.EntityManager;

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

    @Scheduled(fixedRate = 5000)
    public void monitorFinancialAssetVolatility() {
        List<StocksToMonitor> stocksToMonitor = entityManager.createQuery("SELECT s FROM StocksToMonitor s", StocksToMonitor.class).getResultList();
        
        for (StocksToMonitor stock : stocksToMonitor) {
            YahooQuoteClient yahooQuoteClient = new YahooQuoteClient();
            YahooQuoteClient stockInformation = yahooQuoteClient.getFinancialInformation(stock.getStock());
            if (stockInformation != null) {
                FinancialAssetVolatility financialAssetVolatility = new FinancialAssetVolatility(stock.getStock(), stockInformation.getClose_price(), stockInformation.getChange_in_percent(), stockInformation.getRecord_timestamp());
                saveFinancialAssetVolatility(financialAssetVolatility);
            }
            else {
                throw new RuntimeException("Stock information not found");
            }
        } 
    }

    @Transactional
    public void saveFinancialAssetVolatility(FinancialAssetVolatility financialAssetVolatility) {
        financialAssetVolatilityRepository.save(financialAssetVolatility);
    }
}
