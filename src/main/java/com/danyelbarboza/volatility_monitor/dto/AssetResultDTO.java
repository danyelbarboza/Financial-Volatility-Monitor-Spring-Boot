package com.danyelbarboza.volatility_monitor.dto;

import com.danyelbarboza.volatility_monitor.dto.HistoricalDataPriceDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetResultDTO {
    private String symbol;
    private List<HistoricalDataPriceDTO> historicalDataPrice;
}