package com.italia.marxmind.bris.bean;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.italia.marxmind.bris.application.ClientInfo;
import com.italia.marxmind.bris.application.DailyReport;
import com.italia.marxmind.bris.controller.AccessAllowed;
import com.italia.marxmind.bris.controller.BarangayConf;
import com.italia.marxmind.bris.controller.Login;
import com.italia.marxmind.bris.controller.UserConfigMngt;
import com.italia.marxmind.bris.controller.UserDtls;
import com.italia.marxmind.bris.database.Conf;
import com.italia.marxmind.bris.enm.Bris;
import com.italia.marxmind.bris.enm.Feature;
import com.italia.marxmind.bris.reader.ReadConfig;
import com.italia.marxmind.bris.security.License;
import com.italia.marxmind.bris.security.Module;
import com.italia.marxmind.bris.security.SecureChar;
import com.italia.marxmind.bris.sessions.IBean;
import com.italia.marxmind.bris.sessions.SessionBean;
import com.italia.marxmind.bris.utils.DateUtils;
import com.italia.marxmind.bris.utils.LogU;
import com.italia.marxmind.bris.utils.Whitelist;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import lombok.Data;

/**
 * 
 * @author mark italia
 * @since 09/27/2016
 *
 */

@Named
@ViewScoped
@Data
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1094801825228386363L;
	
	private String name;
	private String password;
	private String errorMessage;
	private Login login;
	private String keyPress;
	private int businessId;
	private List business;
	private Map<Integer, BarangayConf> businessData;
	private static final String REPORT_PATH = ReadConfig.value(Bris.REPORT);
	private String ui;
	
	private List themes;
	private String idThemes;
	
	public String getCurrentDate(){//MMMM d, yyyy
		 DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		 Date date_ = new Date();
		 String _date = dateFormat.format(date_);
		return _date;
	}
	
	
	
	/* */
	
	@PostConstruct
	public void init(){
			  setUi("");
		      keyPress = "logId";
			  businessData = new HashMap<Integer, BarangayConf>();
			  business = new ArrayList<>();
			  for(BarangayConf bz : BarangayConf.readBusinessXML()){
					business.add(new SelectItem(bz.getId(), bz.getBarangay()));
					businessData.put(bz.getId(), bz);
			  }
			  idThemes="vela";
			  themes = new ArrayList<>();
			  themes.add(new SelectItem("arya","SHADOW BLACK"));
			  themes.add(new SelectItem("saga","ICE LIGHT"));
			  themes.add(new SelectItem("vela","AURORA BLACK"));
				
			  System.out.println("Running report");
			  System.out.println("=======================================BEFORE TIME " + DateUtils.getCurrentDateYYYYMMDDTIME());
			  DailyReport.runReport(); 
			  System.out.println("=======================================AFTER TIME " + DateUtils.getCurrentDateYYYYMMDDTIME());

				
	}
	
	public void copyWallpaperImg(String wallpaper){
		String pathToSave = FacesContext.getCurrentInstance()
                .getExternalContext().getRealPath(Bris.SEPERATOR.getName()) + Bris.SEPERATOR.getName() +
                Bris.APP_RESOURCES_LOC.getName() + Bris.SEPERATOR.getName() + 
                Bris.BUSSINES_WALLPAPER_IMG.getName() + Bris.SEPERATOR.getName();
		
		File logdirectory = new File(pathToSave);
		if(logdirectory.isDirectory()){
			//System.out.println("is directory");
		}
		
		
		String productFile = ReadConfig.value(Bris.APP_IMG_FILE) + wallpaper;
		File file = new File(productFile);
		//if(!file.exists()){
			//System.out.println("copying file.... " + file.getName());
			try{
			Files.copy(file.toPath(), (new File(pathToSave + file.getName())).toPath(),
			        StandardCopyOption.REPLACE_EXISTING);
			}catch(IOException e){}
			
		//}
	}
	
	public void copyLogo(String filoLogo){
		String pathToSave = FacesContext.getCurrentInstance()
                .getExternalContext().getRealPath(Bris.SEPERATOR.getName()) + Bris.SEPERATOR.getName() +
                Bris.APP_RESOURCES_LOC.getName() + Bris.SEPERATOR.getName() + 
                Bris.BUSSINES_WALLPAPER_IMG.getName() + Bris.SEPERATOR.getName();
		
		File logdirectory = new File(pathToSave);
		if(logdirectory.isDirectory()){
			//System.out.println("is directory");
		}
		
		//System.out.println("Check path to save: " + pathToSave);
		
		File file = new File(filoLogo);
		
			try{
			Files.copy(file.toPath(), (new File(pathToSave)).toPath(),
			        StandardCopyOption.REPLACE_EXISTING);
			}catch(IOException e){}
		
	}
	
	//validate login
	public String validateUserNamePassword(){
		
		Conf.refreshDBInformation();//refresh database information
		
		 HttpSession ses = SessionBean.getSession();
		 try{ses.setAttribute("barangayid", getBusinessId());}catch(Exception e) {ses.setAttribute("barangayid", "0");}
		 boolean isOk = false;
		
		Login in = Login.validUser(Whitelist.remove(getName()), getPassword()); 
		 
		String result="login";
		LogU.add("Guest with username : " + name + " and password ********* is trying to log in the system. is valid? " + isOk);
		if(in != null){
			
			System.out.println("user validated");
			
	        HttpSession session = SessionBean.getSession();
	        session.setAttribute("username", name);
			session.setAttribute("userid", in.getLogid());
			session.setAttribute("ui", getUi());
			session.setAttribute("theme",getIdThemes());
			session.setAttribute("barangayid", getBusinessId());
			boolean isExpired = License.checkLicenseExpiration(Module.BRIS);
			
			
			result = redirectUserPage(in.getUserDtls());
			result += getUi();
			
			LogU.add("The user has been successfully login to the application with the username : " + name + " and password *********");
			
			if(isExpired){
				LogU.add("The application is expired. Please contact application owner.");
				result = "expired";
			}else{
				logUserIn(in);
			}
			
			//run report once everyday
			 /* if("".equalsIgnoreCase(getUi())) {
				  System.out.println("Running report");
				  System.out.println("=======================================BEFORE TIME " + DateUtils.getCurrentDateYYYYMMDDTIME());
				  DailyReport.runReport(); 
				  System.out.println("=======================================AFTER TIME " + DateUtils.getCurrentDateYYYYMMDDTIME());
			  }*/
			
			//recording user configuration file
			String confUser = UserConfigMngt.logUserConfig(getIdThemes(),getUi());
			session.setAttribute("confUser",confUser);
		}else{
			FacesContext.getCurrentInstance().addMessage(
					null,new FacesMessage(
							FacesMessage.SEVERITY_WARN, 
							"Incorrect username and password", 
							"Please enter correct username and password"
							)
					);
//			/setErrorMessage("Incorrect username and password.");
			LogU.add("The user was not successfully login to the application with the username : " + name + " and password *********");
			setName("");
			setPassword("");
			result= "login";
		}
		//System.out.println(getErrorMessage());
		return result;
	}
	
	public String validateUserNamePasswordMobile() {
		//if(getBusinessId()==0){
				//set database on on business type
				getBusiness();//load barangay
				setBusinessId(0);
				//changeDatabaseConnection();
				//}
				
				
				String sql = "SELECT * FROM login WHERE username=?";// and password=?";
				
				String[] params = new String[1];
				         params[0] = Whitelist.remove(name);
				         //params[1] = Whitelist.remove(password);
				Login in = null;
				try{in = Login.retrieve(sql, params).get(0);}catch(Exception e){}
				
				boolean isOk = false;
				if(in!=null){
					String pass1=password;
					String pass2=SecureChar.decodePassword(in.getPassword());
					if(pass1.equalsIgnoreCase(pass2)){
						isOk=true;
					}
				}
				
				String result="mobilelogin";
				LogU.add("Guest with username : " + name + " and password ********* is trying to log in the system.");
				if(isOk){
					
					result = "mobilemail";
			        HttpSession session = SessionBean.getSession();
			        session.setAttribute("username", name);
					session.setAttribute("userid", in.getLogid());
					
					boolean isExpired = License.checkLicenseExpiration(Module.BRIS);
					
			        
					
					LogU.add("The user has been successfully login to the application with the username : " + name + " and password *********");
					
					if(isExpired){
						LogU.add("The application is expired. Please contact application owner.");
						result = "expired";
					}else{
						logUserIn(in);
					}
					
					
				}else{
					FacesContext.getCurrentInstance().addMessage(
							null,new FacesMessage(
									FacesMessage.SEVERITY_WARN, 
									"Incorrect username and password", 
									"Please enter correct username and password"
									)
							);
//					/setErrorMessage("Incorrect username and password.");
					LogU.add("The user was not successfully login to the application with the username : " + name + " and password *********");
					setName("");
					setPassword("");
					result= "mobilelogin";
				}
				System.out.println(getErrorMessage());
				return result;
	}
	
	private String redirectUserPage(UserDtls user){
		String page = "main";
		for(AccessAllowed acc : AccessAllowed.retrieve(" AND ac.isactiveaccess=1  AND usr.userdtlsid="+user.getUserdtlsid() + " limit 1", new String[0])){
			if(Feature.CLEARANCE.getName().equalsIgnoreCase(acc.getFeatures().getModuleName())){
				page = "clearance";
			}else if(Feature.ID_GENERATION.getName().equalsIgnoreCase(acc.getFeatures().getModuleName())){
				page = "card";
			}else if(Feature.BUSINESS.getName().equalsIgnoreCase(acc.getFeatures().getModuleName())){
				page = "adminBusiness";
			}else if(Feature.ASSISTANT.getName().equalsIgnoreCase(acc.getFeatures().getModuleName())){
				page = "assistant";
			}else if(Feature.GRAPH.getName().equalsIgnoreCase(acc.getFeatures().getModuleName())){
				page = "graph";
			}else if(Feature.ORGANIZATION.getName().equalsIgnoreCase(acc.getFeatures().getModuleName())){
				page = "organization";
			}else if(Feature.CHEQUE.getName().equalsIgnoreCase(acc.getFeatures().getModuleName())){
				page = "cheque";
			}else if(Feature.MOE_METER.getName().equalsIgnoreCase(acc.getFeatures().getModuleName())){
				page = "moemeter";
			}else if(Feature.BLOTTERS.getName().equalsIgnoreCase(acc.getFeatures().getModuleName())){
				page = "blotters";
			}else if(Feature.PROFILE_DIRECTORY.getName().equalsIgnoreCase(acc.getFeatures().getModuleName())){
				page = "main";
			}else if(Feature.CITIZEN_REGISTRATION.getName().equalsIgnoreCase(acc.getFeatures().getModuleName())){
				page = "admincustomer";
			}else if(Feature.APPLICATION_USER.getName().equalsIgnoreCase(acc.getFeatures().getModuleName())){
				page = "adminuser";
			}else if(Feature.EMPLOYEE.getName().equalsIgnoreCase(acc.getFeatures().getModuleName())){
				page = "adminEmployees";
			}
		}
		return page;
	}
	
	private void logUserIn(Login in){
		if(in==null) in = new Login();
		ClientInfo cinfo = new ClientInfo();
		in.setLogintime(DateUtils.getCurrentDateMMDDYYYYTIME());
		in.setIsOnline(1);
		in.setClientip(cinfo.getClientIP());
		in.setClientbrowser(cinfo.getBrowserName());
		in.save();
	}
	
	private void logUserOut(){
		String sql = "SELECT * FROM login WHERE username=? and logid=?";
		HttpSession session = SessionBean.getSession();
		String userid = session.getAttribute("userid").toString();
		String username = session.getAttribute("username").toString();
		String[] params = new String[2];
    	params[0] = username;
    	params[1] = userid;
    	Login in = null;
    	try{in = Login.retrieve(sql, params).get(0);}catch(Exception e){}
		ClientInfo cinfo = new ClientInfo();
		if(in!=null){
			in.setLastlogin(DateUtils.getCurrentDateMMDDYYYYTIME());
			in.setIsOnline(0);
			in.setClientip(cinfo.getClientIP());
			in.setClientbrowser(cinfo.getBrowserName());
			in.save();
		}
		LogU.add("The user " + username + " was logging out to the application.");
		
		//Remove registered bean in session
		IBean.removeBean();
		
	}
	
	//logout event, invalidate session
	public String logout(){
		logUserOut();
		setName("");
		setPassword("");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect-true";
	}
	
}


