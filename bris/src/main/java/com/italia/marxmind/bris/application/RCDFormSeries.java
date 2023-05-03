package com.italia.marxmind.bris.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author Mark Italia
 * @version 1.0
 * @since 06/19/2019
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class RCDFormSeries {

	private String id="";
	private String name="";
	private String beginningQty="";
	private String beginningFrom="";
	private String beginningTo="";
	private String receiptQty="";
	private String receiptFrom="";
	private String receiptTo="";
	private String issuedQty="";
	private String issuedFrom="";
	private String issuedTo="";
	private String endingQty="";
	private String endingFrom="";
	private String endingTo="";
	
}
