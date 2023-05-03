package com.italia.marxmind.bris.application;

import java.io.Serializable;
import com.italia.marxmind.bris.controller.ThemesDecoder;
import com.italia.marxmind.bris.enm.Bris;
import com.italia.marxmind.bris.reader.ReadConfig;
import com.italia.marxmind.bris.sessions.SessionBean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
/**
 * 
 * @author mark italia
 * @since 04/09/2017
 * @version 1.0
 *
 */
@Named
@SessionScoped
public class ThemeBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 147868854437557L;

	public String getApplicationTheme(){
	 
		String theme = "nova-colored";
		try{
			HttpSession session = SessionBean.getSession();
			theme = session.getAttribute("theme").toString();
		}catch(Exception e){}
		return theme;
	}
}

