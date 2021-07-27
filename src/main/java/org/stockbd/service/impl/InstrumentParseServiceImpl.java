package org.stockbd.service.impl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.stockbd.service.InstrumentParseService;
import org.stockbd.util.InstrumentUtil;

public class InstrumentParseServiceImpl implements InstrumentParseService {
	
	@Override
	public Elements getInstrument(String instrumentName) {
		String url = InstrumentUtil.getInstrumentUrl(instrumentName);
		Elements instrument = null;
		
		try {
			Document doc = Jsoup.connect(url).get();
			instrument = doc.select("#company tbody");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return instrument;
	}

}
