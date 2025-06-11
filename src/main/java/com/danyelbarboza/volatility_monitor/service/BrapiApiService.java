package com.danyelbarboza.volatility_monitor.service;

import com.danyelbarboza.volatility_monitor.dto.BrapiResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BrapiApiService {

    private final RestTemplate restTemplate;

    public BrapiApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BrapiResponseDTO getAssetHistoricalData(String ticker) {
        String url = String.format("https://brapi.dev/api/quote/%s?range=" + "14d" + "&interval=" + "1d", ticker);
        BrapiResponseDTO response = restTemplate.getForObject(url, BrapiResponseDTO.class);

        return response;
    }
}