package org.stockbd;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.select.Elements;
import org.stockbd.model.Instrument;
import org.stockbd.service.impl.BasicInfoParseServiceImpl;
import org.stockbd.service.impl.InstrumentListServiceImpl;
import org.stockbd.service.impl.InstrumentParseServiceImpl;
import org.stockbd.service.impl.MarketInfoParseServiceImpl;

public class Main {

	public static void main(String[] args) {		
		InstrumentListServiceImpl instrumentService = new InstrumentListServiceImpl();
		List<String> instrumentNames = instrumentService.fetchInstrumentNames();
		
		InstrumentParseServiceImpl instrumentParseService = new InstrumentParseServiceImpl();
		MarketInfoParseServiceImpl marketInfoParseService = new MarketInfoParseServiceImpl();
		BasicInfoParseServiceImpl basicInfoParseService = new BasicInfoParseServiceImpl();		
		
		List<Instrument> instrumentList = new ArrayList<>();
		
		for (String instrumentName : instrumentNames) {
			try {
				Instrument instrument = new Instrument(instrumentName);
				
				Elements elements = instrumentParseService.getInstrument(instrumentName);
				marketInfoParseService.getInfo(elements, instrument);
				basicInfoParseService.getInfo(elements, instrument);
				
				System.out.println(instrument);
				System.out.println("*********************************");
				instrumentList.add(instrument);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

	}

}
