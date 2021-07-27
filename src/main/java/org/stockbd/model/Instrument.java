package org.stockbd.model;

import lombok.Data;

@Data
public class Instrument {
	
	public Instrument(String name) {
		setName(name);
	}
	
	private String name;
	private String sector;
	private MarketInfo marketInfo;
	private BasicInfo basicInfo;
}
