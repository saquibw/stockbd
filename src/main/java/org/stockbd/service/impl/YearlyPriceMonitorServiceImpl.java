package org.stockbd.service.impl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.stockbd.service.YearlyPriceMonitorService;

public class YearlyPriceMonitorServiceImpl implements YearlyPriceMonitorService {

	@Override
	public void parse() {
		try {
			Document doc = Jsoup.connect("https://www.dsebd.org/latest_share_price_scroll_l.php").get();
			
			Elements instruments = doc.select(".shares-table tbody tr");
			System.out.println(instruments.size());
			
			for (Element elem : instruments) {
				Elements item = elem.select("td");
				String itemName = item.get(1).text();
				
				parseItem(itemName);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void parseItem(String itemName) {
		String itemUrl = "https://www.dsebd.org/displayCompany.php?name=" + itemName;
		
		try {
			Document doc = Jsoup.connect(itemUrl).get();
			
			Elements companyInfo = doc.select("#company tbody tr");
			
			Element currentPriceElement = companyInfo.get(1);
			//System.out.println(currentPriceElement);
			String currentPriceText = currentPriceElement.select("td").get(0).text();
			 if (currentPriceText.equals("-")) {
				 return;
			 }
			double currentPrice = Double.parseDouble(currentPriceText.replace(",", ""));
			
			Element highLowElement = companyInfo.get(4);
			String highLowText = highLowElement.select("td").get(1).text();			
			double low = Double.parseDouble(highLowText.split("-")[0].trim().replace(",", ""));
			double high = Double.parseDouble(highLowText.split("-")[1].trim().replace(",", ""));
			
			double percentage = ((currentPrice - low) / (high - low)) * 100;
			
			System.out.println(String.format("%s -- %.2f -- %.2f -- %.2f -- %.2f%%", itemName, currentPrice, low, high, percentage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
