package com.italia.marxmind.bris.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.italia.marxmind.bris.database.ConnectDB;
import com.italia.marxmind.bris.enm.Feature;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author mark italia
 * @since 08/05/2017
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Features {

	private int id;
	private String moduleName;
	private int isActive;
	private String monthExpiration;
	private String activationCode;
	private Timestamp timestamp;
	
	private boolean checked;
	
	public static List<Features> retrieve(String sql, String[] params){
		List<Features> fets = Collections.synchronizedList(new ArrayList<Features>());
		
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
			Features fet = new Features();
			try{fet.setId(rs.getInt("fid"));}catch(NullPointerException e){}
			try{fet.setModuleName(rs.getString("modulename"));}catch(NullPointerException e){}
			try{fet.setIsActive(rs.getInt("isActive"));}catch(NullPointerException e){}
			try{fet.setMonthExpiration(rs.getString("monthexp"));}catch(NullPointerException e){}
			try{fet.setActivationCode(rs.getString("activationcode"));}catch(NullPointerException e){}
			fet.setChecked(fet.getIsActive()==1? true : false);
			fets.add(fet);
		}
		
		rs.close();
		ps.close();
		ConnectDB.close(conn);
		}catch(Exception e){e.getMessage();}
		
		return fets;
	}

	public static boolean isEnabled(Feature fets){
		//Features fet = new Features();
		
		String sql = "SELECT * FROM features WHERE modulename=? AND isActive=1";
		String[] params = new String[1];
		params[0] = fets.getName();
		
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
		
		System.out.println("FEATURE : " + ps.toString());
		
		rs = ps.executeQuery();
		
		while(rs.next()){
			
			return true;
			/*fet.setId(rs.getInt("fid"));
			fet.setModuleName(rs.getString("modulename"));
			fet.setIsActive(rs.getInt("isActive"));
			fet.setMonthExpiration(rs.getString("monthexp"));
			fet.setActivationCode(rs.getString("activationcode"));*/
			
		}
		
		rs.close();
		ps.close();
		ConnectDB.close(conn);
		}catch(Exception e){e.getMessage();}
		
		return false;
	}
	
	public static void save(Feature feat, boolean isEnabled){
		String sql ="";
		if(isEnabled){
			sql = "UPDATE features set isActive=1 WHERE modulename='" + feat.getName() +"'";
		}else{
			sql = "UPDATE features set isActive=0 WHERE modulename='" + feat.getName() +"'";
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			
			conn = ConnectDB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		}catch(Exception e){}
		
	}
	
	public static void saveData(Features features, boolean isEnabled){
		String sql ="";
		if(isEnabled){
			sql = "UPDATE features set isActive=1 WHERE modulename='" + features.getModuleName() +"'";
		}else{
			sql = "UPDATE features set isActive=0 WHERE modulename='" + features.getModuleName() +"'";
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			
			conn = ConnectDB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		}catch(Exception e){}
		
	}
	
	public static void saveVAT(Features features,double value){
		String sql ="";
		
			sql = "UPDATE features set vat="+value+" WHERE modulename='" + features.getModuleName() +"'";
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			
			conn = ConnectDB.getConnection();
			ps = conn.prepareStatement(sql);
			System.out.println("Update vat " + ps.toString());
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		}catch(Exception e){}
		
	}
}

