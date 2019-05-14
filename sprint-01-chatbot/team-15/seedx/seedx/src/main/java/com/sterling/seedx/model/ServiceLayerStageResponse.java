package com.sterling.seedx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceLayerStageResponse {
	
	private String SequenceNo;
	private String Name;
	private String Stageno;
	private String ClosingPercentage;
	private String Cancelled;
	private String IsSales;
	private String IsPurchasing;
	@JsonProperty("odata.metadata")
	private String odata_metadata;
	
	@JsonProperty("SequenceNo")
	public String getSequenceNo() {
		return SequenceNo;
	}
	public void setSequenceNo(String sequenceNo) {
		SequenceNo = sequenceNo;
	}
	
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	@JsonProperty("Stageno")
	public String getStageno() {
		return Stageno;
	}
	public void setStageno(String stageno) {
		Stageno = stageno;
	}
	
	@JsonProperty("ClosingPercentage")
	public String getClosingPercentage() {
		return ClosingPercentage;
	}
	public void setClosingPercentage(String closingPercentage) {
		ClosingPercentage = closingPercentage;
	}
	
	@JsonProperty("Cancelled")
	public String getCancelled() {
		return Cancelled;
	}
	public void setCancelled(String cancelled) {
		Cancelled = cancelled;
	}
	
	@JsonProperty("IsSales")
	public String getIsSales() {
		return IsSales;
	}
	public void setIsSales(String isSales) {
		IsSales = isSales;
	}
	
	@JsonProperty("IsPurchasing")
	public String getIsPurchasing() {
		return IsPurchasing;
	}
	public void setIsPurchasing(String isPurchasing) {
		IsPurchasing = isPurchasing;
	}
	
	public String getOdata_metadata() {
		return odata_metadata;
	}
	public void setOdata_metadata(String odata_metadata) {
		this.odata_metadata = odata_metadata;
	}
	
	
	

}