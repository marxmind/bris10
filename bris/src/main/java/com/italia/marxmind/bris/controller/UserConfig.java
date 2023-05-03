package com.italia.marxmind.bris.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author Mark Italia
 * @version 1.0
 * @since 09/04/2019
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class UserConfig {

	private String fileName;
	private String themeSelected;
	private String doNotNotifyAgainToday;
	private String versionUsing;
	private String barangay;
	
	
}
