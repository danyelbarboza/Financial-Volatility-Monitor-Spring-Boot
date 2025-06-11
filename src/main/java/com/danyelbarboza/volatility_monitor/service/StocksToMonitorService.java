package com.danyelbarboza.volatility_monitor.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danyelbarboza.volatility_monitor.dao.StockToMonitorRespository;
import com.danyelbarboza.volatility_monitor.entity.StocksToMonitor;

import jakarta.persistence.EntityManager;

@Service
public class StocksToMonitorService {
    private StockToMonitorRespository stockToMonitorRespository;
    private EntityManager entityManager;
    
    public StocksToMonitorService(StockToMonitorRespository stockToMonitorRespository, EntityManager entityManager) {
        this.stockToMonitorRespository = stockToMonitorRespository;
        this.entityManager = entityManager;
    }
    
    @Transactional
    public void saveStocksToMonitor(StocksToMonitor stocksToMonitor) {
        stockToMonitorRespository.save(stocksToMonitor);
    }
    
}
