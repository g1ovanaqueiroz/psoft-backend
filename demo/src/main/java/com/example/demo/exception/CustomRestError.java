package com.example.demo.exception;

import java.util.Date;

/**
 * 
 * @author Giovana Brito Oliveira
 *
 */
public class CustomRestError {

	private Date date;
	private String msg;
	private String description;

	/**
	 * CustomRestError constructor
	 * 
	 * @param date        date
	 * @param msg         msg
	 * @param description description
	 */
	public CustomRestError(Date date, String msg, String description) {
		this.date = date;
		this.msg = msg;
		this.description = description;
	}

	/**
	 * Returns the date
	 * 
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Updates the date
	 * 
	 * @param date date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Returns the msg
	 * 
	 * @return msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Updates the msg
	 * 
	 * @param msg msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * Returns the description
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Updates the description
	 * 
	 * @param description description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
