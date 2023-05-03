package com.italia.marxmind.bris.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author Mark Italia
 * @since 08/02/2018
 * @version 1.0
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class TransmittalRpt {
	
	private long id;
	private String dvDate;
	private String dvNumber;
	private String checkDate;
	private String checkNumber;
	private String payee;
	private String amount;
	private String pbcDate;
	private String pbcNumber;
	
}
