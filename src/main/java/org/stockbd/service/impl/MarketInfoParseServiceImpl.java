package org.stockbd.service.impl;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.stockbd.constant.InstrumentConstant;
import org.stockbd.model.Instrument;
import org.stockbd.model.MarketInfo;
import org.stockbd.service.InfoParseService;
import org.stockbd.util.InstrumentUtil;

public class MarketInfoParseServiceImpl implements InfoParseService {

	@Override
	public Instrument getInfo(Elements elements, Instrument instrument) {
		Element marketInfoElement = elements.get(InstrumentConstant.MARKET_INFO_INDEX);
		
		Elements marketInfoDetails = marketInfoElement.select("tr");
		
		MarketInfo marketInfo = new MarketInfo(
				getLastTradePrice(marketInfoDetails), 
				get52WeeksHigh(marketInfoDetails), 
				get52WeeksLow(marketInfoDetails), 
				getDaysHigh(marketInfoDetails), 
				getDaysLow(marketInfoDetails), 
				getDaysValue(marketInfoDetails), 
				getDaysVolume(marketInfoDetails), 
				getDaysTrade(marketInfoDetails));
		
		instrument.setMarketInfo(marketInfo);
		
		return instrument;
	}
	
	private double getLastTradePrice(Elements marketInfoDetails) {
		Element row = marketInfoDetails.get(0);
		String lastTradePriceText = row.select("td").get(0).text();
		
		
		double lastTradePrice = Double.parseDouble(InstrumentUtil.sanitize(lastTradePriceText));
		
		return lastTradePrice;
	}
	
	private double getDaysLow(Elements marketInfoDetails) {
		Element row = marketInfoDetails.get(1);
		String rangeText = row.select("td").get(1).text();
		
		return Double.parseDouble(InstrumentUtil.sanitize(rangeText.split("-")[0].trim()));
	}
	
	private double getDaysHigh(Elements marketInfoDetails) {
		Element row = marketInfoDetails.get(1);
		String rangeText = row.select("td").get(1).text();
		
		return Double.parseDouble(InstrumentUtil.sanitize(rangeText.split("-")[1].trim()));
	}
	
	private double get52WeeksLow(Elements marketInfoDetails) {
		Element row = marketInfoDetails.get(3);
		String rangeText = row.select("td").get(1).text();
		
		return Double.parseDouble(InstrumentUtil.sanitize(rangeText.split("-")[0].trim()));
	}
	
	private double get52WeeksHigh(Elements marketInfoDetails) {
		Element row = marketInfoDetails.get(3);
		String rangeText = row.select("td").get(1).text();
		
		return Double.parseDouble(InstrumentUtil.sanitize(rangeText.split("-")[1].trim()));
	}
	
	private double getDaysValue(Elements marketInfoDetails) {
		Element row = marketInfoDetails.get(2);
		String valueText = row.select("td").get(1).text();

		return Double.parseDouble(InstrumentUtil.sanitize(valueText));
	}
	
	private double getDaysVolume(Elements marketInfoDetails) {
		Element row = marketInfoDetails.get(4);
		String valueText = row.select("td").get(1).text();

		return Double.parseDouble(InstrumentUtil.sanitize(valueText));
	}
	
	private long getDaysTrade(Elements marketInfoDetails) {
		Element row = marketInfoDetails.get(5);
		String valueText = row.select("td").get(1).text();

		return Long.parseLong(InstrumentUtil.sanitize(valueText));
	}

}
