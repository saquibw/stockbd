package org.stockbd.service;

import org.jsoup.select.Elements;

public interface InstrumentParseService {
	public Elements getInstrument(String instrumentName);
}
