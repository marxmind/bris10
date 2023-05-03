package com.italia.marxmind.bris.controller;

import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import com.italia.marxmind.bris.enm.Bris;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author mark italia
 * @since 12/02/2017
 * @version 1.0
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class BusinessEngaged {
	
	private int id;
	private String name;
	
	private static final String BUSINESS_FILE = Bris.PRIMARY_DRIVE.getName() + Bris.SEPERATOR.getName() + 
			Bris.APP_FOLDER.getName() + Bris.SEPERATOR.getName() +
			Bris.APP_CONFIG_FLDR.getName() + Bris.SEPERATOR.getName() +
			"BusinessEngaged.bris";
	
	public static List<BusinessEngaged> readBusinessEngagedXML(){
    	List<BusinessEngaged> lines = Collections.synchronizedList(new ArrayList<BusinessEngaged>());
    	try {
            	
            	Properties prop = new Properties();
            	prop.load(new FileInputStream(BUSINESS_FILE));
            	int size = Integer.valueOf(prop.getProperty("total"));
            	
            	for(int i=0; i<size; i++) {
            		BusinessEngaged b = new BusinessEngaged();
            		b.setId(i+1);
            		b.setName(prop.getProperty("label"+i));
            		lines.add(b);
            	}
            	
            
           } catch (Exception e) {
            e.printStackTrace();
           }
    	return lines;
    }
	
	public static BusinessEngaged businessName(int id){
    	BusinessEngaged line = new BusinessEngaged();
    	try {
    		Properties prop = new Properties();
        	prop.load(new FileInputStream(BUSINESS_FILE));
        		
        	line.setId(id);
        	line.setName(prop.getProperty("label"+id));
           } catch (Exception e) {
            e.printStackTrace();
           }
    	return line;
    }
}
