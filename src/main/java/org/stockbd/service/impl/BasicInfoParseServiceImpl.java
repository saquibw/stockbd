package org.stockbd.service.impl;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.stockbd.constant.InstrumentConstant;
import org.stockbd.model.BasicInfo;
import org.stockbd.model.Instrument;
import org.stockbd.service.InfoParseService;
import org.stockbd.util.InstrumentUtil;

public class BasicInfoParseServiceImpl implements InfoParseService {

	@Override
	public Instrument getInfo(Elements elements, Instrument instrument) {
		Element basicInfoElement = elements.get(InstrumentConstant.BASIC_INFO_INDEX);
		
		Elements basicInfoDetails = basicInfoElement.select("tr");
		
		BasicInfo basicInfo = new BasicInfo(
				getAuthorizedCapital(basicInfoDetails), 
				getPaidUpCapital(basicInfoDetails), getNumberOfSecurities(basicInfoDetails), getDebutTradingDate(basicInfoDetails));
		
		instrument.setBasicInfo(basicInfo);
		instrument.setSector(getSector(basicInfoDetails));
		
		return instrument;
	}
	
	private double getAuthorizedCapital(Elements basicInfoDetails) {
		Element row = basicInfoDetails.get(0);
		String authCapText = row.select("td").get(0).text();
		authCapText = authCapText.equals("-") ? "0" : authCapText;
		
		return Double.parseDouble(InstrumentUtil.sanitize(authCapText));
	}
	
	private double getPaidUpCapital(Elements basicInfoDetails) {
		Element row = basicInfoDetails.get(1);
		String paidUpCapText = row.select("td").get(0).text();
		
		return Double.parseDouble(InstrumentUtil.sanitize(paidUpCapText));
	}
	
	private double getNumberOfSecurities(Elements basicInfoDetails) {
		Element row = basicInfoDetails.get(3);
		String num = row.select("td").get(0).text();
		
		return Double.parseDouble(InstrumentUtil.sanitize(num));
	}
	
	private String getDebutTradingDate(Elements basicInfoDetails) {
		Element row = basicInfoDetails.get(0);
		
		return row.select("td").get(1).text();
	}
	
	private String getSector(Elements basicInfoDetails) {
		Element row = basicInfoDetails.get(3);
		
		return row.select("td").get(1).text();
	}

}
