package com.italia.marxmind.bris.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.italia.marxmind.bris.database.ConnectDB;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Signatory {

	private int sigId;
	private String sigName;
	private String sigPosition;
	private String sigCreated;
	
	public static List<Signatory> retrieve(String sql, String[] params){
		List<Signatory> sigs =  Collections.synchronizedList(new ArrayList<Signatory>());
		
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
		
		//System.out.println("SQL " + ps.toString());
		
		
		rs = ps.executeQuery();
		
		while(rs.next()){
			
			Signatory sig = new Signatory();
			try{sig.setSigId(rs.getInt("sig_id"));}catch(NullPointerException e){}
			try{sig.setSigName(rs.getString("sig_name"));}catch(NullPointerException e){}
			try{sig.setSigPosition(rs.getString("sig_position"));}catch(NullPointerException e){}
			try{sig.setSigCreated(rs.getString("sig_created"));}catch(NullPointerException e){}
			sigs.add(sig);
			
		}
		rs.close();
		ps.close();
		ConnectDB.close(conn);
		}catch(SQLException sl){}
		
		return sigs;
	}
	
	public static Map<String, Signatory> retrieveSig(String sql, String[] params){
		Map<String,Signatory> sigs = java.util.Collections.synchronizedMap(new HashMap<String,Signatory>());
		
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
		
		//System.out.println("SQL " + ps.toString());
		
		
		rs = ps.executeQuery();
		
		while(rs.next()){
			
			Signatory sig = new Signatory();
			try{sig.setSigId(rs.getInt("sig_id"));}catch(NullPointerException e){}
			try{sig.setSigName(rs.getString("sig_name"));}catch(NullPointerException e){}
			try{sig.setSigPosition(rs.getString("sig_position"));}catch(NullPointerException e){}
			try{sig.setSigCreated(rs.getString("sig_created"));}catch(NullPointerException e){}
			sigs.put(sig.getSigId()+"", sig);
			
		}
		rs.close();
		ps.close();
		ConnectDB.close(conn);
		}catch(SQLException sl){}
		
		return sigs;
	}
}
