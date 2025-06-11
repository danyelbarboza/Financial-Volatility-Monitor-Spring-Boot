package com.danyelbarboza.volatility_monitor.client;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class YahooQuoteClient {

    private Stock stock;
    private BigDecimal close_price;
    private BigDecimal atr_14;
    private Timestamp record_timestamp;
    
    public YahooQuoteClient() {
    }

    public YahooQuoteClient(Stock stock, BigDecimal close_price, BigDecimal atr_14, Timestamp record_timestamp) {
        this.stock = stock;
        this.close_price = close_price;
        this.atr_14 = atr_14;
        this.record_timestamp = record_timestamp;
    }

    public YahooQuoteClient getFinancialInformation(String ticker) {
        try {
            Stock stock = YahooFinance.get(ticker);
            BigDecimal close_price = stock.getQuote().getPrice();
            BigDecimal atr_14 = stock.getQuote().getChangeInPercent();
            Timestamp record_timestamp = new Timestamp(System.currentTimeMillis());

            YahooQuoteClient stockInformation = new YahooQuoteClient(stock, close_price, atr_14, record_timestamp);
            
            return stockInformation;
        } catch (Exception e) {
            return null;
        }
    }
}
