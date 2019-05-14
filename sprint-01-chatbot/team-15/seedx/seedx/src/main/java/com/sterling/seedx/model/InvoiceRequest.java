package com.sterling.seedx.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceRequest {

	public int top;
	

	public InvoiceRequest() {
		super();
	}

	@JsonProperty("top")
	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}
	
	
}
