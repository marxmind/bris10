package com.italia.marxmind.bris.application;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;

import com.italia.marxmind.bris.enm.Bris;

public class Menu {

	private static final String MENUFILE = Bris.PRIMARY_DRIVE.getName() + Bris.SEPERATOR.getName() + 
			Bris.APP_FOLDER.getName() + Bris.SEPERATOR.getName() +
			Bris.APP_CONFIG_FLDR.getName() + Bris.SEPERATOR.getName() + "menu.bris";
	
	private static final String APPLICATION_FILE = Bris.PRIMARY_DRIVE.getName() + Bris.SEPERATOR.getName() + 
			Bris.APP_FOLDER.getName() + Bris.SEPERATOR.getName() +
			Bris.APP_CONFIG_FLDR.getName() + Bris.SEPERATOR.getName() +
			Bris.APP_CONFIG_FILE.getName();
	
	private int id;
	private String[] labels;

public static void main(String[] args) {
	int size = 5;
	Bris[] bris = new Bris[size];
	String[] value = new String[size];
	bris[0] = Bris.DB_NAME; value[0]="bris_talisay";
	bris[1] = Bris.IDCODE; value[1]="TLS";
	bris[2] = Bris.PROVINCE; value[2]="South Cotabato";
	bris[3] = Bris.MUNICIPALITY; value[3]="Lake Sebu";
	bris[4] = Bris.BARANGAY; value[4]="Talisay";
	updateMenu(bris, value);
}	
	
	public static String[] readMenuXML(){
		String[] menu = new String[19]; 
		try {
			
			Properties prop = new Properties();
			prop.load(new FileInputStream(MENUFILE));
			for(int i=0; i<19; i++) {
				menu[i] = prop.getProperty("menu"+i);
			}
			
		}catch(Exception e) {}
		return menu;
	}
public static void updateMenu(Bris[] tag, String[] value){
		
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
}
