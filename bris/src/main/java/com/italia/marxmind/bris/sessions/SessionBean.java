package com.italia.marxmind.bris.sessions;

import java.io.Serializable;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
/**
 * 
 * @author mark italia
 * @since created 09/27/2016
 *
 */

public class SessionBean implements Serializable{

	private static final long serialVersionUID = 1094801825228386363L;
	
	public static HttpSession getSession(){
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	public static HttpServletRequest getRequest(){
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	public static String getUserName(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("username").toString();
	}
	public static String getUserId(){
		HttpSession session = getSession();
		if(session != null){
			return (String)session.getAttribute("userid");
		}else{
			return null;
		}
	}
	public static String getUIDesign() {
		HttpSession session = getSession();
		if(session != null){
			return (String)session.getAttribute("ui");
		}else{
			return "";
		}
	}
	
	
}
