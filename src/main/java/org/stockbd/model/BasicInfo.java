package org.stockbd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicInfo {
	private double authorizedCapital;
	private double paidUpCapital;
	private double numberOfSecurities;
	private String debutTradingDate;
}
