package com.italia.marxmind.bris.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.italia.marxmind.bris.database.ConnectDB;
import com.italia.marxmind.bris.enm.Bris;
import com.italia.marxmind.bris.reader.ReadConfig;
import com.italia.marxmind.bris.utils.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author mark italia
 * @since 09/28/2016
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Copyright {
	
	private Long id;
	private String copyrightname;
	private String expdate;
	private String appname;
	private String currentversion;
	private String oldversion;
	private String author;
	private String contactno;
	private String email;
	private Timestamp timestamp;
	
	public static List<Copyright> retrieve(String sql, String[] params){
		List<Copyright> data = new ArrayList<>();//Collections.synchronizedList(new ArrayList<>());
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
		conn = ConnectDB.getConnection();
		ps = conn.prepareStatement(sql);
		
		if(params!=null && params.length>0){
			
			for(int i=0; i<params.length; i++){
				ps.setString(i+1, params[i]);
			}
			
		}
		
		rs = ps.executeQuery();
		
		while(rs.next()){
			Copyright app = new Copyright();
			try{app.setId(rs.getLong("id"));}catch(NullPointerException e){}
			try{app.setCopyrightname(rs.getString("copyrightname"));}catch(NullPointerException e){}
			try{app.setExpdate(rs.getString("expdate"));}catch(NullPointerException e){}
			try{app.setAppname(rs.getString("appname"));}catch(NullPointerException e){}
			try{app.setCurrentversion(rs.getString("currentversion"));}catch(NullPointerException e){}
			try{app.setOldversion(rs.getString("oldversion"));}catch(NullPointerException e){}
			try{app.setAuthor(rs.getString("author"));}catch(NullPointerException e){}
			try{app.setContactno(rs.getString("contactno"));}catch(NullPointerException e){}
			try{app.setEmail(rs.getString("email"));}catch(NullPointerException e){}
			try{app.setTimestamp(rs.getTimestamp("timestamp"));}catch(NullPointerException e){}
			data.add(app);
		}
		rs.close();
		ps.close();
		ConnectDB.close(conn);
		}catch(Exception e){}
		return data;
	}
	
	private static String xmlLicense(){
		return ReadConfig.value(Bris.APP_EXP);
	}
	private static String dbLicense(){
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
		String sql = "SELECT expdate FROM copyright ORDER BY id desc limit 1";	
		conn = ConnectDB.getConnection();
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while(rs.next()){
			return rs.getString("expdate");
		}
		
		rs.close();
		ps.close();
		ConnectDB.close(conn);
		}catch(Exception e){}
		
		return null;
	}
	
	public static boolean checkLicenseExpiration(){
		
		String dblicense = dbLicense();
		if(dblicense==null) return false;
		char[] month = dblicense.split("-")[0].toCharArray();
		int m1 = Integer.valueOf(month[0]+"");
		int m2 = Integer.valueOf(month[1]+"");
		
		char[] day = dblicense.split("-")[1].toCharArray();
		int d1 = Integer.valueOf(day[0]+"");
		int d2 = Integer.valueOf(day[1]+"");
		
		
		char[] year = dblicense.split("-")[2].toCharArray();
		int y1 = Integer.valueOf(year[0]+"");
		int y2 = Integer.valueOf(year[1]+"");
		int y3 = Integer.valueOf(year[2]+"");
		int y4 = Integer.valueOf(year[3]+"");
		
		String chkVal = months()[m1]+months()[m2] +"-"+ days()[d1]+days()[d2] +"-"+ years()[y1]+years()[y2]+years()[y3]+years()[y4]; 
		
		//System.out.println("xml : " + xmlLicense() );
		System.out.println("dblicense : " + dblicense + " converted " + chkVal);
		
		if(xmlLicense().equalsIgnoreCase(chkVal)){
			
			return checkdate(dblicense);
			
		}
		
		return true;
		
	}
	
	/**
	 * 
	 * @return true if expired
	 */
	private static boolean checkdate(String dbLicense){
		
		
		String systemDate = DateUtils.getCurrentDateMMDDYYYY();
		
		boolean isYear = false;
		boolean isMonth = false;
		boolean isDay = false;
		int sxMonth = Integer.valueOf(systemDate.split("-")[0]);
		int sxDay = Integer.valueOf(systemDate.split("-")[1]);
		int sxYear = Integer.valueOf(systemDate.split("-")[2]);
		
		int dbMonth = Integer.valueOf(dbLicense.split("-")[0]);
		int dbDay = Integer.valueOf(dbLicense.split("-")[1]);
		int dbYear = Integer.valueOf(dbLicense.split("-")[2]);
		
		
		//first check year
		if(dbYear<=sxYear){
			isYear = true;
		}
		//second check month
		if(dbMonth<=sxMonth){
			isMonth = true;
		}
		//third check day
		if(dbDay<=sxDay){
			isDay = true;
		}
		
		if(isYear && isMonth && isDay){
			return true;
		}
		
		/*if(db<=sx){
			//System.out.println("true");
			return true;
		}*/
		
		return false;
	}
	
	private static String[] days(){
		char[] addChar = "markitalia".toCharArray();
		String[] days = new String[10];
		for(int i=0; i<=9;i++){
			days[i] = "0"+i+addChar[i];
		}
		return days;
	}
	
	private static String[] months(){
		char[] addChar = "mritaliamark".toCharArray();
		String[] months = new String[12];
		for(int i=0; i<12;i++){
			months[i] = "0" + (i+1) + addChar[i];
		}
		return months;
	}
	
	private static String[] years(){
		char[] addChar = "markitalia".toCharArray();
		String[] years = new String[12];
		for(int i=0; i<=9;i++){
			years[i] = "0" + i + addChar[i];
		}
		return years;
	}
	
	
}












