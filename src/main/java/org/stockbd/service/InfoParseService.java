package org.stockbd.service;

import org.jsoup.select.Elements;
import org.stockbd.model.Instrument;

public interface InfoParseService {
	public Instrument getInfo(Elements elements, Instrument instrument);
}
