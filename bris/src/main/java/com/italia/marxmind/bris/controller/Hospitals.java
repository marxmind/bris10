package com.italia.marxmind.bris.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
public class Hospitals {

	private int id;
	private String name;
	private String address;
	private int isActive;
	
	public static List<Hospitals> retrieve(String sql, String[] params){
		List<Hospitals> hps = new ArrayList<>();
		
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
			
			Hospitals ph = new Hospitals();
			
			try{ph.setId(rs.getInt("hopid"));}catch(NullPointerException e){}
			try{ph.setName(rs.getString("hname"));}catch(NullPointerException e){}
			try{ph.setAddress(rs.getString("haddress"));}catch(NullPointerException e){}
			try{ph.setIsActive(rs.getInt("isactive"));}catch(NullPointerException e){}
			
			hps.add(ph);
			
		}
		
		rs.close();
		ps.close();
		ConnectDB.close(conn);
		}catch(Exception e){e.getMessage();}
		
		return hps;
	}
}
