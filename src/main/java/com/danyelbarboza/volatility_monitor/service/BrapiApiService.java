package com.danyelbarboza.volatility_monitor.service;

import com.danyelbarboza.volatility_monitor.dto.BrapiResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BrapiApiService {

    private final RestTemplate restTemplate;
    @Value("${brapi.api.token}")
    private String apiToken;

    public BrapiApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BrapiResponseDTO getAssetHistoricalData(String ticker) {
        String url = String.format("https://brapi.dev/api/quote/%s?range=" + "1mo" + "&interval=" + "1d" + "&token=" + apiToken, ticker);
        BrapiResponseDTO response = restTemplate.getForObject(url, BrapiResponseDTO.class);

        return response;
    }
}