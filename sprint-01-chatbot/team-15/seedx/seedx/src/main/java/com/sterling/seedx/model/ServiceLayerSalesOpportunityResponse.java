package com.sterling.seedx.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceLayerSalesOpportunityResponse {
	
	private int SequentialNo;
	private String CardCode;
	private double MaxLocalTotal;
	private double WeightedSumLC;
	private double ClosingPercentage;
	private String OpportunityName;
	private String CustomerName;
	private String StageName;
	private int CurrentStageNo;
	private ServiceLayerStageResponse stage = new ServiceLayerStageResponse();
	
	@JsonProperty("SequentialNo")
	public int getSequentialNo() {
		return SequentialNo;
	}
	public void setSequentialNo(int sequentialNo) {
		SequentialNo = sequentialNo;
	}
	
	@JsonProperty("CardCode")
	public String getCardCode() {
		return CardCode;
	}
	public void setCardCode(String cardCode) {
		CardCode = cardCode;
	}
	
	@JsonProperty("MaxLocalTotal")
	public double getMaxLocalTotal() {
		return MaxLocalTotal;
	}
	public void setMaxLocalTotal(double maxLocalTotal) {
		MaxLocalTotal = maxLocalTotal;
	}
	
	@JsonProperty("WeightedSumLC")
	public double getWeightedSumLC() {
		return WeightedSumLC;
	}
	public void setWeightedSumLC(double weightedSumLC) {
		WeightedSumLC = weightedSumLC;
	}
	
	@JsonProperty("ClosingPercentage")
	public double getClosingPercentage() {
		return ClosingPercentage;
	}
	public void setClosingPercentage(double closingPercentage) {
		ClosingPercentage = closingPercentage;
	}
	
	@JsonProperty("OpportunityName")
	public String getOpportunityName() {
		return OpportunityName;
	}
	public void setOpportunityName(String opportunityName) {
		OpportunityName = opportunityName;
	}
	
	@JsonProperty("CustomerName")
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	
	@JsonProperty("StageName")
	public String getStageName() {
		return StageName;
	}
	public void setStageName(String stageName) {
		StageName = stageName;
	}
	
	
	@JsonProperty("CurrentStageNo")
	public int getCurrentStageNo() {
		return CurrentStageNo;
	}
	public void setCurrentStageNo(int currentStageNo) {
		CurrentStageNo = currentStageNo;
	}
	
	public ServiceLayerStageResponse getStage() {
		return stage;
	}
	public void setStage(ServiceLayerStageResponse stage) {
		this.stage = stage;
	}
	
	
	
	
}
