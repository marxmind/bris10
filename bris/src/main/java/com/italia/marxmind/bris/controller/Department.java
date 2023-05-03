package com.italia.marxmind.bris.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.italia.marxmind.bris.database.ConnectDB;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 
 * @author mark italia
 * @since 09/27/2016
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Department {

	private int depid;
	private String departmentName;
	private UserDtls userDtls;
	private BarangayConf company;
	private Timestamp timestamp;
	
	
	public static String departmentSQL(String tablename,Department dep){
		String sql="";
		if(dep!=null){
			
			if(dep.getDepid()!=0){
				sql += " AND "+ tablename +".departmentid=" + dep.getDepid();
			}
			if(dep.getDepartmentName()!=null){
				sql += " AND "+ tablename +".departmentname='" + dep.getDepartmentName()+"'";
			}
			
		}
		
		return sql;
	}
	
	public static List<Department> retrieve(String sql, String[] params){
		List<Department> deps = Collections.synchronizedList(new ArrayList<Department>());
		
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
			Department dep = new Department();
			try{dep.setDepid(rs.getInt("departmentid"));}catch(NullPointerException e){}
			try{dep.setDepartmentName(rs.getString("departmentname"));}catch(NullPointerException e){}
			deps.add(dep);
		}
		rs.close();
		ps.close();
		ConnectDB.close(conn);
		}catch(Exception e){}
		
		return deps;
	}
	
	public static Department department(String departmentid){
		Department dep = new Department();
		String sql = "SELECT * FROM department WHERE departmentid=?";
		String[] params = new String[1];
		params[0] = departmentid;
		try{
			dep = Department.retrieve(sql, params).get(0);
		}catch(Exception e){}
		return dep;
	}
}

