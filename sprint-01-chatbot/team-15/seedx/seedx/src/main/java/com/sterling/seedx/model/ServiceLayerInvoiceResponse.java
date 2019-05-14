package com.sterling.seedx.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceLayerInvoiceResponse {

	private int DocEntry;
	private int DocNum;
	private String DocType;
	private Date DocDate;
	private String CardCode;
	private String CardName;
	private double DocTotal;
	
	@JsonProperty("DocEntry")
	public int getDocEntry() {
		return DocEntry;
	}
	public void setDocEntry(int docEntry) {
		DocEntry = docEntry;
	}
	
	@JsonProperty("DocNum")
	public int getDocNum() {
		return DocNum;
	}
	public void setDocNum(int docNum) {
		DocNum = docNum;
	}
	
	@JsonProperty("DocType")
	public String getDocType() {
		return DocType;
	}
	public void setDocType(String docType) {
		DocType = docType;
	}

	
	@JsonProperty("DocDate")
	public Date getDocDate() {
		return DocDate;
	}
	public void setDocDate(Date docDate) {
		DocDate = docDate;
	}
	
	
	@JsonProperty("CardCode")
	public String getCardCode() {
		return CardCode;
	}
	public void setCardCode(String cardCode) {
		CardCode = cardCode;
	}
	
	@JsonProperty("CardName")
	public String getCardName() {
		return CardName;
	}
	public void setCardName(String cardName) {
		CardName = cardName;
	}
	
	@JsonProperty("DocTotal")
	public double getDocTotal() {
		return DocTotal;
	}
	public void setDocTotal(double docTotal) {
		DocTotal = docTotal;
	}
	
	
	
	
}
