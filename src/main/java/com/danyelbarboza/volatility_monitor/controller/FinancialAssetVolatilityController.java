package com.danyelbarboza.volatility_monitor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danyelbarboza.volatility_monitor.entity.FinancialAssetVolatility;
import com.danyelbarboza.volatility_monitor.service.FinancialAssetVolatilityService;

@RestController
@RequestMapping("/api/v1")
public class FinancialAssetVolatilityController {

    private FinancialAssetVolatilityService financialAssetVolatilityService;
    
    @Autowired
    public FinancialAssetVolatilityController(FinancialAssetVolatilityService financialAssetVolatilityService) {
        this.financialAssetVolatilityService = financialAssetVolatilityService;
    }
    
    @GetMapping("/finav")
    public ResponseEntity<List<FinancialAssetVolatility>> getAllFinancialAssetVolatility() {
        List<FinancialAssetVolatility> financialAssetVolatilityList = financialAssetVolatilityService.getAllFinancialAssetVolatility();
        return ResponseEntity.ok(financialAssetVolatilityList);
    }

    @PostMapping("/monitor")
    public ResponseEntity<String> monitorFinancialAssetVolatility() {
        try {
            financialAssetVolatilityService.monitorFinancialAssetVolatility();
            return ResponseEntity.ok("Financial asset volatility monitored successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /*
    @GetMapping("/finav/{id}")
    public ResponseEntity<FinancialAssetVolatility> getFinancialAssetVolatilityById(@PathVariable Long id) {
        FinancialAssetVolatility financialAssetVolatility = financialAssetVolatilityService.getFinancialAssetVolatilityById(id);
        return ResponseEntity.ok(financialAssetVolatility);
    }

    @GetMapping("/finav/{stock}")
    public ResponseEntity<FinancialAssetVolatility> getFinancialAssetVolatilityByStock(@PathVariable String stock) {
        FinancialAssetVolatility financialAssetVolatility = financialAssetVolatilityService.getFinancialAssetVolatilityByStock(stock);
        return ResponseEntity.ok(financialAssetVolatility);
    }
    */
}
