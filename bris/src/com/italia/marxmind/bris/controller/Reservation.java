package com.italia.marxmind.bris.controller;

/**
 * 
 * @author mark italia
 * @since 05/05/2017
 * @version 1.0
 *
 */
public class Reservation {

	private String id;
	private String description;
	private String startDate;
	private String endDate;
	
	private String memos;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getMemos() {
		return memos;
	}
	public void setMemos(String memos) {
		this.memos = memos;
	}
	
}

