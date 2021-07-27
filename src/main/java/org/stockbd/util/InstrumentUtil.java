package org.stockbd.util;

import org.stockbd.constant.InstrumentConstant;

public class InstrumentUtil {
	public static String getInstrumentUrl(String instrumentName) {
		return new StringBuilder(InstrumentConstant.INSTRUMENT_BASE_URL).append(instrumentName).toString();
	}
	
	public static String sanitize(String data) {
		return data.replace(",", "");
	}
	
	public static double convertMnToNumeric(double number) {
		return number * 1000000;
	}
}
