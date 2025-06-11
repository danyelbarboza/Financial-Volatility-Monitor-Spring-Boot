package com.danyelbarboza.volatility_monitor.client;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Data
public class YahooQuoteClient {

    private Stock stock;
    private BigDecimal close_price;
    private BigDecimal change_in_percent;
    private Timestamp record_timestamp;
    
    public YahooQuoteClient() {
    }

    public YahooQuoteClient(Stock stock, BigDecimal close_price, BigDecimal change_in_percent, Timestamp record_timestamp) {
        this.stock = stock;
        this.close_price = close_price;
        this.change_in_percent = change_in_percent;
        this.record_timestamp = record_timestamp;
    }

    public YahooQuoteClient getFinancialInformation(String stock) {
        try {
            Stock stockInformation = YahooFinance.get(stock);
            BigDecimal close_price = stockInformation.getQuote().getPrice();
            BigDecimal change_in_percent = stockInformation.getQuote().getChangeInPercent();
            Timestamp record_timestamp = new Timestamp(System.currentTimeMillis());

            YahooQuoteClient result = new YahooQuoteClient(stockInformation, close_price, change_in_percent, record_timestamp);
            
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
