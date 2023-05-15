package com.italia.marxmind.bris.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.italia.marxmind.bris.enm.Bris;

/**
 * 
 * @author mark italia
 * @since 09/09/2017
 * @version 1.0
 *
 */

@Deprecated
public class Themes {

	private int id;
	private String styleName;
	
	private static final String THEMES_FILE = Bris.PRIMARY_DRIVE.getName() + Bris.SEPERATOR.getName() + 
			Bris.APP_FOLDER.getName() + Bris.SEPERATOR.getName() +
			Bris.APP_CONFIG_FLDR.getName() + Bris.SEPERATOR.getName() +
			Bris.THEMES.getName();
	
	private static final String APPLICATION_FILE = Bris.PRIMARY_DRIVE.getName() + Bris.SEPERATOR.getName() + 
			Bris.APP_FOLDER.getName() + Bris.SEPERATOR.getName() +
			Bris.APP_CONFIG_FLDR.getName() + Bris.SEPERATOR.getName() +
			Bris.APP_CONFIG_FILE.getName();
	
	public Themes() {}
	
	public Themes(int id, String styleName) {
		this.id = id;
		this.styleName = styleName;
	}
	
	 public static void updateThemes(Bris[] tag, String[] value){
			
			
		}
	
	public static List<Themes> readThemesXML(){
    	List<Themes> themes = new ArrayList<Themes>();
		
    	return themes;
    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	
}
