package com.italia.marxmind.bris.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import com.italia.marxmind.bris.enm.Bris;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 
 * @author mark italia
 * @since 09/27/2016
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class BarangayConf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 44657681L;
	private int id;
	
	
	private String menuStyle;
	
	private String database;
	private String userName;
	private String password;
	private String bcode;
	private String province;
	private String municipality;
	private String barangay;
	
	private String appExp;
	private String logInlcude;
	private String logPath;
	private String imgPath;
	private String reportPath;
	private String themeStyle;
	
	private String driver;
	private String url;
	private String ssl;
	private String port;
	
	private static final String BARAMGAY_FILE = Bris.PRIMARY_DRIVE.getName() + Bris.SEPERATOR.getName() + 
			Bris.APP_FOLDER.getName() + Bris.SEPERATOR.getName() +
			Bris.APP_CONFIG_FLDR.getName() + Bris.SEPERATOR.getName() +
			Bris.BARANGAY_FILE.getName();
	
	private static final String APPLICATION_FILE = Bris.PRIMARY_DRIVE.getName() + Bris.SEPERATOR.getName() + 
			Bris.APP_FOLDER.getName() + Bris.SEPERATOR.getName() +
			Bris.APP_CONFIG_FLDR.getName() + Bris.SEPERATOR.getName() +
			Bris.APP_CONFIG_FILE.getName();
	
    public static List<BarangayConf> retrieveProduct(String sql, String[] params){
		
		List<BarangayConf> business = Collections.synchronizedList(new ArrayList<BarangayConf>());
		
		return business;
	}
    
    public static void updateBusiness(Bris[] tag, String[] value){
		
    	try {
		
			Parameters params = new Parameters();
			FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
	                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
	                        .configure(params.properties().setFile(new File(APPLICATION_FILE)));
			Configuration cfg = builder.getConfiguration();
			
			for(int i=0; i<tag.length; i++) {
				cfg.setProperty(tag[i].getName(), value[i]);
			}
			builder.save();
			
		}catch(Exception e) {e.printStackTrace();}
    	
		
	}
	
    public static List<BarangayConf> readBusinessXML(){
    	List<BarangayConf> bars = Collections.synchronizedList(new ArrayList<BarangayConf>());
    	
    	try {
    	Properties prop = new Properties();
		prop.load(new FileInputStream(BARAMGAY_FILE));
    	int size = Integer.valueOf(prop.getProperty("numberOfBarangay"));
		for(int i=0; i<size; i++) {
			BarangayConf bar = new BarangayConf();
			bar.setId(i);
			bar.setDatabase(prop.getProperty("databaseName"+i));
			bar.setDriver(prop.getProperty("driver"+i));
			bar.setUrl(prop.getProperty("url"+i));
			bar.setPort(prop.getProperty("port"+i));
			bar.setSsl(prop.getProperty("SSL"+i));
			bar.setUserName(prop.getProperty("username"+i));
			bar.setPassword(prop.getProperty("password"+i));
			bar.setAppExp(prop.getProperty("applicationExp"+i));
			bar.setLogInlcude(prop.getProperty("includeLog"+i));
			bar.setLogPath(prop.getProperty("logPath"+i));
			bar.setImgPath(prop.getProperty("imgPath"+i));
			bar.setReportPath(prop.getProperty("reports"+i));
			bar.setThemeStyle(prop.getProperty("themeStyle"+i));
			bar.setBcode(prop.getProperty("idcode"+i));
			bar.setBarangay(prop.getProperty("barangayName"+i));
			bar.setProvince(prop.getProperty("province"+i));
			bar.setMunicipality(prop.getProperty("municipality"+i));
			bar.setMenuStyle(prop.getProperty("menustyle"+i));
			bars.add(bar);
		}
    	}catch(Exception e) {}
		
    	return bars;
    }
}

