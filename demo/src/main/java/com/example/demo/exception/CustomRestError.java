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

    public CustomRestError(Date date, String msg, String description) {
        this.date = date;
        this.msg = msg;
        this.description = description;
    }

    // it returns the date
	public Date getDate() {
		return date;
	}

	// it updates the date
	public void setDate(Date date) {
		this.date = date;
	}

	// it returns the msg
	public String getMsg() {
		return msg;
	}

	// it updates the msg
	public void setMsg(String msg) {
		this.msg = msg;
	}

	// it returns the description
	public String getDescription() {
		return description;
	}

	// it updates the description
	public void setDescription(String description) {
		this.description = description;
	}

}
