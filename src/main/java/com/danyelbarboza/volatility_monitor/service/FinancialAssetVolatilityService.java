package com.danyelbarboza.volatility_monitor.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danyelbarboza.volatility_monitor.dao.FinancialAssetVolatilityRepository;
import com.danyelbarboza.volatility_monitor.dto.AssetResultDTO;
import com.danyelbarboza.volatility_monitor.dto.BrapiResponseDTO;
import com.danyelbarboza.volatility_monitor.dto.HistoricalDataPriceDTO;
import com.danyelbarboza.volatility_monitor.entity.FinancialAssetVolatility;
import com.danyelbarboza.volatility_monitor.entity.StocksToMonitor;

import jakarta.persistence.EntityManager;

@Service
public class FinancialAssetVolatilityService {
    private FinancialAssetVolatilityRepository financialAssetVolatilityRepository;
    private EntityManager entityManager;
    private BrapiApiService brapiApiService;
    private AtrCalculationService atrCalculationService;
    
    
    
    public FinancialAssetVolatilityService(FinancialAssetVolatilityRepository financialAssetVolatilityRepository, EntityManager entityManager, BrapiApiService brapiApiService, AtrCalculationService atrCalculationService) {
        this.financialAssetVolatilityRepository = financialAssetVolatilityRepository;
        this.entityManager = entityManager;
        this.brapiApiService = brapiApiService;
        this.atrCalculationService = atrCalculationService;
    }
    
    public List<FinancialAssetVolatility> getAllFinancialAssetVolatility() {
        return financialAssetVolatilityRepository.findAll();
    }

    @Scheduled(cron = "0 0 18 * * MON-FRI")
    @Transactional
    public void monitorAllFinancialAssetVolatility() {
        List<StocksToMonitor> stocksToMonitor = entityManager.createQuery("SELECT s FROM StocksToMonitor s", StocksToMonitor.class).getResultList();
        System.out.println("Iniciando monitoramento para " + stocksToMonitor.size() + " ativos...");

        
        for (StocksToMonitor stock : stocksToMonitor) {
            BrapiResponseDTO stockInformation = brapiApiService.getAssetHistoricalData(stock.getStock());
            try {
            if (stockInformation == null || stockInformation.getResults() == null || stockInformation.getResults().isEmpty()) {
                System.err.println("Não foram encontrados resultados para o ticker: " + stock.getStock());
                continue;
            }
            
            AssetResultDTO result = stockInformation.getResults().get(0);
            List<HistoricalDataPriceDTO> historicalData = result.getHistoricalDataPrice();

            if (historicalData == null || historicalData.isEmpty()) {
                System.err.println("Não foram encontrados dados históricos para o ticker: " + stock.getStock());
                continue;
            }
            
            BigDecimal atr14 = atrCalculationService.calculate(historicalData);

            BigDecimal closePrice = historicalData.get(historicalData.size() - 1).getClose();

            FinancialAssetVolatility financialAssetVolatility = new FinancialAssetVolatility();
            financialAssetVolatility.setTicker(result.getSymbol());
            financialAssetVolatility.setClosePrice(closePrice);
            financialAssetVolatility.setAtr14(atr14);
            financialAssetVolatility.setRecordTimestamp(Timestamp.valueOf(LocalDateTime.now()));

            financialAssetVolatilityRepository.save(financialAssetVolatility);
            System.out.println("Dados de volatilidade salvos para: " + result.getSymbol());

        } catch (Exception e) {
            System.err.println("Falha ao processar o ticker: " + stock.getStock() + " - Erro: " + e.getMessage());
        }
    }
    System.out.println("Monitoramento finalizado.");
    } 

    @Transactional
    public void monitorFinancialAssetVolatility(String stock) {
        BrapiResponseDTO stockInformation = brapiApiService.getAssetHistoricalData(stock);
        try {
            if (stockInformation == null || stockInformation.getResults() == null || stockInformation.getResults().isEmpty()) {
                System.err.println("Não foram encontrados resultados para o ticker: " + stock);
            }
            
        AssetResultDTO result = stockInformation.getResults().get(0);
        List<HistoricalDataPriceDTO> historicalData = result.getHistoricalDataPrice();

        if (historicalData == null || historicalData.isEmpty()) {
            System.err.println("Não foram encontrados dados históricos para o ticker: " + stock);
        }
            
        BigDecimal atr14 = atrCalculationService.calculate(historicalData);

        BigDecimal closePrice = historicalData.get(historicalData.size() - 1).getClose();

        FinancialAssetVolatility financialAssetVolatility = new FinancialAssetVolatility();
        financialAssetVolatility.setTicker(result.getSymbol());
        financialAssetVolatility.setClosePrice(closePrice);
        financialAssetVolatility.setAtr14(atr14);
        financialAssetVolatility.setRecordTimestamp(Timestamp.valueOf(LocalDateTime.now()));

        financialAssetVolatilityRepository.save(financialAssetVolatility);
        System.out.println("Dados de volatilidade salvos para: " + result.getSymbol());

        } catch (Exception e) {
            System.err.println("Falha ao processar o ticker: " + stock + " - Erro: " + e.getMessage());
        }
    }


    @Transactional
    public void saveFinancialAssetVolatility(FinancialAssetVolatility financialAssetVolatility) {
        financialAssetVolatilityRepository.save(financialAssetVolatility);
    }

    @Transactional
    public void deleteAllFinancialAssetVolatility() {
        financialAssetVolatilityRepository.deleteAll();
    }
}
