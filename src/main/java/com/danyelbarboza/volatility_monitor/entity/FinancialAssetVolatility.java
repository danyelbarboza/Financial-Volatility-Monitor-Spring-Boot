package com.danyelbarboza.volatility_monitor.entity;

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
    private Double closePrice;

    @Column(name = "atr_14")
    private Double atr14;

    @Column(name = "record_timestamp")
    private Timestamp recordTimestamp;
}
