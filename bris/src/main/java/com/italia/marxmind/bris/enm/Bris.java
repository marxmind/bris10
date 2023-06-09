package com.italia.marxmind.bris.enm;

import java.io.File;
/**
 * 
 * @author mark italia
 * @since created 09/27/2016
 * @version 1.0
 *
 */
public enum Bris {

	DB_NAME("databaseName"),
	DB_DRIVER("driver"),
	DB_URL("url"),
	DB_PORT("port"),
	DB_SSL("SSL"),
	USER_NAME("username"),
	USER_PASS("password"),
	APP_EXP("applicationExp"),
	APP_VER("applicationVersion"),
	APP_COPYRIGHT("copyright"),
	APP_OWNER("author"),
	APP_EMAIL("supportEamil"),
	APP_PHONE("supportNo"),
	APP_LOG_PATH("logPath"),
	APP_LOG("includeLog"),
	APP_FOLDER("bris"),
	APP_CONFIG_FLDR("conf"),
	//APP_CONFIG_FILE("application.properties"),
	APP_CONFIG_FILE("barangay.bris"),
	APP_LICENSE_FILE_NAME("license.bris"),
	APP_IMG_FILE("imgPath"),
	APP_BUSINESS_TYPE("businessType"),
	APP_RESOURCES_LOC("resources"),
	APP_RESOURCES_LOC_IMG("img"),
	SECURITY_ENCRYPTION_FORMAT("utf-8"),
	PRIMARY_DRIVE(System.getenv("SystemDrive")),
	SEPERATOR(File.separator),
	REPORT_CONFIG_FILENAME("reports.bris"),
	REPORT("reports"),
	BARANGAY_NAME("barangayName"),
	BUSINESS_WALLPAPER_FILE("businessWallpaperFile"),
	BUSSINES_WALLPAPER_IMG("gif"),
	THEME_STYLE("themeStyle"),
	BARANGAY_FILE("barangay.bris"),
	RECEIPT_INFO("receiptsinfo.xml"),
	RECEIPTS_LOG("receiptLog"),
	IDCODE("idcode"),
	PROVINCE("province"),
	MUNICIPALITY("municipality"),
	BARANGAY("barangay"),
	YEARLY_BUDGET("yearlybudget"),
	THEMES("themes.xml"),
	MENUSTYLE("menustyle"),
	RESERVATION_FILE_XML("reservation.xml"),
	DB_TIMEZONE("serverTimezone=UTC");
	
	private String name;
	
	
	private Bris(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
