package com.danyelbarboza.volatility_monitor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danyelbarboza.volatility_monitor.entity.StocksToMonitor;
import com.danyelbarboza.volatility_monitor.service.StocksToMonitorService;

@RestController
@RequestMapping("/api/stocks")
public class StocksToMonitorController {
    
    private StocksToMonitorService stocksToMonitorService;
    
    @Autowired
    public StocksToMonitorController(StocksToMonitorService stocksToMonitorService) {
        this.stocksToMonitorService = stocksToMonitorService;
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<StocksToMonitor>> getAllStocksToMonitor() {
        List<StocksToMonitor> stocksToMonitorList = stocksToMonitorService.getAllStocksToMonitor();
        return ResponseEntity.ok(stocksToMonitorList);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStocksToMonitor(@RequestBody StocksToMonitor stocksToMonitor) {
        stocksToMonitorService.addStocksToMonitor(stocksToMonitor.getStock());
        return ResponseEntity.ok("Stock added successfully");
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateStocksToMonitor(@PathVariable Long id, @RequestBody StocksToMonitor stocksToMonitor) {
        stocksToMonitorService.updateStocksToMonitor(id, stocksToMonitor.getStock());
        return ResponseEntity.ok("Stock updated successfully");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteStocksToMonitor(@PathVariable Long id) {
        stocksToMonitorService.deleteStocksToMonitor(id);
        return ResponseEntity.ok("Stock deleted successfully");
    }

    @PostMapping("/deleteAll")
    public ResponseEntity<String> deleteAllStocksToMonitor() {
        stocksToMonitorService.deleteAllStocksToMonitor();
        return ResponseEntity.ok("All stocks deleted successfully");
    }
}
