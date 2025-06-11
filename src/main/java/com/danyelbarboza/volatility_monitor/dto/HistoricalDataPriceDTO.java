package com.danyelbarboza.volatility_monitor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricalDataPriceDTO {
    private long date;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;

}