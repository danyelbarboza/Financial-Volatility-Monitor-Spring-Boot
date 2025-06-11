package com.danyelbarboza.volatility_monitor.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.danyelbarboza.volatility_monitor.entity.StocksToMonitor;

@RepositoryRestResource(path = "stocksmonitor")
public interface StockToMonitorRespository extends JpaRepository<StocksToMonitor, Long> {
    
}
