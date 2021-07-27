package org.stockbd.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.stockbd.constant.InstrumentConstant;
import org.stockbd.service.InstrumentListService;

public class InstrumentListServiceImpl implements InstrumentListService {

	@Override
	public List<String> fetchInstrumentNames() {
		List<String> instrumentList = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(InstrumentConstant.INSTRUMENT_LIST_URL).get();
			Elements instruments = doc.select(".shares-table tbody tr");
			
			instruments.stream().forEach(i -> {
				String instrumentName  = i.select("td").get(1).text();
				instrumentList.add(instrumentName);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return instrumentList;
	}

}
