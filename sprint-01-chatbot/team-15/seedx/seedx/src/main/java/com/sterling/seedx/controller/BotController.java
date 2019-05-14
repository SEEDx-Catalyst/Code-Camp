package com.sterling.seedx.controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.service.spi.ServiceException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterling.seedx.model.BotResponse;
import com.sterling.seedx.model.Conversation;
import com.sterling.seedx.model.InvoiceRequest;
import com.sterling.seedx.model.LoginB1Credential;
import com.sterling.seedx.model.Reply;
import com.sterling.seedx.model.ServiceLayerInvoiceResponse;
import com.sterling.seedx.model.ServiceLayerSalesOpportunityResponse;
import com.sterling.seedx.model.ServiceLayerStageResponse;
import com.sterling.seedx.service.DataService;

@RestController
@RequestMapping(value = "/bot")
public class BotController {

	ResourceBundle resource = ResourceBundle.getBundle("config");
	String urlServiceLayer = resource.getString("url.servicelayer");
	String portSAP = resource.getString("port.sap");
	String portServiceLayer = resource.getString("port.servicelayer");
	String databaseName = resource.getString("database.name");
	String databaseUserName = resource.getString("database.username");
	String databasePassword = resource.getString("database.password");

	private static final Logger log = Logger.getLogger(BotController.class);
	private final String USER_AGENT = "Mozilla/5.0";
	private final String BaseUrl = "https://" + urlServiceLayer + ":" + portServiceLayer + "/";
	public static final String CONTENTTYPE_JSON = "multipart/mixed;boundary=batch_stem";
	public static final String CONTENTTYPE_URLENCODED = "application/x-www-form-urlencoded";
	public static final String CONTENTTYPE_FORMDATA = "multipart/form-data";

	LoginB1Credential cred = new LoginB1Credential();

	@Autowired
	private DataService dataService;

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public BotResponse testRoot(HttpServletRequest request,
			@RequestParam(value = "number", required = false) int number) {
		log.debug("hit");

		List<ServiceLayerInvoiceResponse> invoiceResponsesList = new ArrayList<>();
		invoiceResponsesList = dataService.getTopInvoice(number);

		BotResponse response = new BotResponse();

		for (int i = 0; i < invoiceResponsesList.size(); i++) {
			Reply r = new Reply();
			r.setType("text");
			r.setContent(message(invoiceResponsesList.get(i)));
			response.getReplies().add(r);
		}

		Conversation c = new Conversation();
		HashMap<String, String> memory = new HashMap<String, String>();
		memory.put("key", "value");
		c.setMemory(memory);

		response.setConversation(c);

		return response;
		
	}

	@RequestMapping(value = { "/invoice" }, method = RequestMethod.POST)
	public BotResponse postLogin(HttpServletRequest request, @RequestBody InvoiceRequest invoiceRequest) {
		log.debug("hit");

		List<ServiceLayerInvoiceResponse> invoiceResponsesList = new ArrayList<>();
		invoiceResponsesList = dataService.getTopInvoice(invoiceRequest.getTop());

		BotResponse response = new BotResponse();

		for (int i = 0; i < invoiceResponsesList.size(); i++) {
			Reply r = new Reply();
			r.setType("text");
			r.setContent(message(invoiceResponsesList.get(i)));
			response.getReplies().add(r);
		}

		Conversation c = new Conversation();
		HashMap<String, String> memory = new HashMap<String, String>();
		memory.put("key", "value");
		c.setMemory(memory);

		response.setConversation(c);

		return response;
	}


	@RequestMapping(value = { "/sales" }, method = RequestMethod.POST)
	public BotResponse postGetTopSales(HttpServletRequest request, @RequestBody InvoiceRequest invoiceRequest) {
		log.debug("hit");

		List<ServiceLayerSalesOpportunityResponse> salesResponsesList = new ArrayList<>();
		salesResponsesList = dataService.getTopSalesOp(invoiceRequest.getTop());
		
		for (int i = 0; i < salesResponsesList.size(); i++) {
			ServiceLayerStageResponse stage = dataService.getStage(salesResponsesList.get(i).getCurrentStageNo());
			salesResponsesList.get(i).setStageName(stage.getName());
		}

		BotResponse response = new BotResponse();

		for (int i = 0; i < salesResponsesList.size(); i++) {
			Reply r = new Reply();
			r.setType("text");
			r.setContent(message(salesResponsesList.get(i)));
			response.getReplies().add(r);
		}

		Conversation c = new Conversation();
		HashMap<String, String> memory = new HashMap<String, String>();
		memory.put("key", "value");
		c.setMemory(memory);

		response.setConversation(c);

		return response;
	}

	
	public String message(ServiceLayerInvoiceResponse response) {
		StringBuilder msg = new StringBuilder();
		msg.append("DocEntry : ");
		msg.append(response.getDocEntry());
		msg.append("\n");
		msg.append("DocNum : ");
		msg.append(response.getDocNum());
		msg.append("\n");
		msg.append("DocType : ");
		msg.append(response.getDocType());
		msg.append("\n");
		msg.append("DocDate : ");
		msg.append(response.getDocDate());
		msg.append("\n");
		msg.append("CardCode : ");
		msg.append(response.getCardCode());
		msg.append("\n");
		msg.append("Card Name : ");
		msg.append(response.getCardName());
		return msg.toString();
	}

	public String message(ServiceLayerSalesOpportunityResponse response) {
		StringBuilder msg = new StringBuilder();
		msg.append("#" + response.getSequentialNo());
		msg.append("(" + (int) response.getClosingPercentage() + "%) / ");
		msg.append((int) response.getWeightedSumLC() + " (weighted amount)");
		msg.append("\n");
		msg.append(response.getCardCode() + "-" + response.getCustomerName());
		msg.append("\n");
		msg.append((int)response.getMaxLocalTotal()+ " (total amount)");
		msg.append("\n");
		msg.append(response.getStageName());
		msg.append("\n");
		return msg.toString();
	}



}
