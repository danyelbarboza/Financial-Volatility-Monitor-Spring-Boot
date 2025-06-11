package com.danyelbarboza.volatility_monitor.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "financial_asset_volatility")
public class FinancialAssetVolatility {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "close_price")
    private BigDecimal closePrice;

    @Column(name = "atr_14")
    private BigDecimal atr14;

    @Column(name = "record_timestamp")
    private Timestamp recordTimestamp;

    public FinancialAssetVolatility() {
    }

    public FinancialAssetVolatility(String ticker, BigDecimal closePrice, BigDecimal atr14, Timestamp recordTimestamp) {
        this.ticker = ticker;
        this.closePrice = closePrice;
        this.atr14 = atr14;
        this.recordTimestamp = recordTimestamp;
    }
}
