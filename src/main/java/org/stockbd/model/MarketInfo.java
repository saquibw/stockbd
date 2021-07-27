package org.stockbd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketInfo {
	private double lastTradePrice;
	private double _52WeeksHigh;
	private double _52WeeksLow;
	private double daysHigh;
	private double daysLow;
	private double daysValue;
	private double daysVolume;
	private long daysTrade;
}
