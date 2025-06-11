package com.danyelbarboza.volatility_monitor.service;

import com.danyelbarboza.volatility_monitor.dto.HistoricalDataPriceDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class AtrCalculationService {

    private final int atrPeriod = 14;

    public BigDecimal calculate(List<HistoricalDataPriceDTO> historico) {
        // pelo menos 15 dias para ter 14 períodos + o fechamento anterior.
        if (historico == null || historico.size() < atrPeriod + 1) {
            System.err.println("Dados históricos insuficientes para calcular o ATR de " + atrPeriod);
            return BigDecimal.ZERO;
        }

        // 1: CALCULAR O "TRUE RANGE" (TR) PARA CADA DIA
        List<BigDecimal> trueRanges = new ArrayList<>();
        for (int i = 1; i < historico.size(); i++) {
            HistoricalDataPriceDTO today = historico.get(i);
            HistoricalDataPriceDTO yesterday = historico.get(i - 1);

            BigDecimal maxMinusMin = today.getHigh().subtract(today.getLow());
            BigDecimal maxMinusClose = today.getHigh().subtract(yesterday.getClose()).abs();
            BigDecimal minMinusClose = today.getLow().subtract(yesterday.getClose()).abs();

            BigDecimal trueRangeOfDay = maxMinusMin
                .max(maxMinusClose)
                .max(minMinusClose);

            trueRanges.add(trueRangeOfDay);
        }

        // 2: CALCULAR O ATR (MÉDIA DOS TRUE RANGES)
        BigDecimal somaDosPrimeirosTRs = BigDecimal.ZERO;
        for (int i = 0; i < atrPeriod; i++) {
            somaDosPrimeirosTRs = somaDosPrimeirosTRs.add(trueRanges.get(i));
        }

        BigDecimal atrAnterior = somaDosPrimeirosTRs.divide(new BigDecimal(atrPeriod), 4, RoundingMode.HALF_UP);

        // O restante dos ATRs usa uma fórmula de suavização para "continuar" a média
        // ATR_atual = ((ATR_anterior * 13) + TR_atual) / 14
        for (int i = atrPeriod; i < trueRanges.size(); i++) {
            BigDecimal trAtual = trueRanges.get(i);
            atrAnterior = (atrAnterior.multiply(new BigDecimal(atrPeriod - 1)))
                .add(trAtual)
                .divide(new BigDecimal(atrPeriod), 4, RoundingMode.HALF_UP);
        }

        // O último valor calculado é o ATR 14 mais recente
        return atrAnterior;
    }
}