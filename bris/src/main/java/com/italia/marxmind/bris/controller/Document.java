package com.italia.marxmind.bris.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author mark italia
 * @since 02/06/2018
 * @version 1.0
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Document {

	private int id;
	private String name;
	
	private List<Document> listDocs;
	private Clearance clearance;
	private Customer customer;
}
