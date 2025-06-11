package com.danyelbarboza.volatility_monitor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrapiResponseDTO {
    private List<AssetResultDTO> results;

}