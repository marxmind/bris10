package com.italia.marxmind.bris.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.italia.marxmind.bris.application.ApplicationFixes;
import com.italia.marxmind.bris.application.ApplicationVersionController;
import com.italia.marxmind.bris.security.Copyright;
import com.italia.marxmind.bris.security.License;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
/**
 * 
 * @author mark italia
 * @since 09/28/2016
 * @version 1.0
 */
@Named("versionBean")
@ViewScoped
@Data
public class ApplicationVersionControllerBean implements Serializable {

	private static final long serialVersionUID = 1394801825228386363L;
	
	private ApplicationVersionController versionController;
	private ApplicationFixes applicationFixes;
	private List<ApplicationFixes> fixes;
	private Copyright copyright;
	private List<License> licenses;
		
	@PostConstruct
	public void init(){
		fixes = new ArrayList<ApplicationFixes>(); 
		licenses = new ArrayList<License>();
		
		String sql = "SELECT * FROM app_version_control ORDER BY timestamp DESC LIMIT 1";
		String[] params = new String[0];
		versionController = ApplicationVersionController.retrieve(sql, params).get(0);
		
		sql = "SELECT * FROM copyright ORDER BY id desc limit 1";
		params = new String[0];
		copyright = Copyright.retrieve(sql, params).get(0);
		
		try{fixes = new ArrayList<ApplicationFixes>();}catch(Exception e){}
		sql = "SELECT * FROM buildfixes WHERE buildid=?";
		params = new String[1];
		params[0] = versionController.getBuildid()+"";
		try{fixes = ApplicationFixes.retrieve(sql, params);}catch(Exception e){}
		
		sql = "SELECT * FROM license";
		licenses = new ArrayList<License>();
		licenses = License.retrieve(sql, new String[0]);
		
	}
	
}
