package com.cbnits.CBNITS_TRADE.Exception;
//java.sql.Date;
import java.util.Date;
import java.util.List;

public class ErrorDetails {
//	  private Date timestamp;
	  private String message;
	  private List<String> details;

	  
	  public ErrorDetails( String message, List<String> details) {
	    super();
//	    this.timestamp = date;
	    this.message = message;
	    this.details = details;
	  }

//	  public String isValid() {
//		    return Boolean.TRUE.toString();
//		}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	

}