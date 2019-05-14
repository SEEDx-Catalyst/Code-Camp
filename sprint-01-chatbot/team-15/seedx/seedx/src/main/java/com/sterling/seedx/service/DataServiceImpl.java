package com.sterling.seedx.service;

import java.util.List;

import com.sterling.seedx.model.BotResponse;
import com.sterling.seedx.model.ServiceLayerInvoiceResponse;
import com.sterling.seedx.model.ServiceLayerSalesOpportunityResponse;
import com.sterling.seedx.model.ServiceLayerStageResponse;

public interface DataServiceImpl {
	List<ServiceLayerInvoiceResponse> getTopInvoice(int nilai);
	List<ServiceLayerSalesOpportunityResponse> getTopSalesOp(int nilai);
	ServiceLayerStageResponse getStage(int stageNo);
}
