package com.sterling.seedx.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.security.cert.CertificateException;
import java.util.ArrayList;
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

import org.hibernate.service.spi.ServiceException;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterling.seedx.controller.BotController;
import com.sterling.seedx.model.BotResponse;
import com.sterling.seedx.model.Conversation;
import com.sterling.seedx.model.LoginB1Credential;
import com.sterling.seedx.model.ServiceLayerInvoiceResponse;
import com.sterling.seedx.model.ServiceLayerSalesOpportunityResponse;
import com.sterling.seedx.model.ServiceLayerStageResponse;

@Service
public class DataService implements DataServiceImpl {

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

	public void loginB1(String dbName, String userSap, String passSap) {
		cred.setCompanyDB(dbName);
		cred.setPassword(passSap);
		cred.setUserName(userSap);

		URL url;
		HttpsURLConnection connection = null;
		try {
			String body = "";
			// Create connection
			url = new URL(BaseUrl + "b1s/v1/Login");
			trustAllHosts();
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("POST");

			ObjectMapper m = new ObjectMapper();
			try {
				body = m.writeValueAsString(cred);
			} catch (IOException iox) {
				log.error("Error parsing JSON", iox);
			}

			connection.setRequestProperty("User-Agent", USER_AGENT);
			connection.setRequestProperty("Content-Type", CONTENTTYPE_JSON);
			connection.setRequestProperty("Content-Length", String.valueOf(body.length()));
			connection.setAllowUserInteraction(false);
			connection.setRequestProperty("Accept", "*/*");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			log.debug("sending data");
			log.debug(body);
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(body);
			wr.flush();
			wr.close();

			log.debug("code login: " + connection.getResponseCode());
			log.debug("message login: " + connection.getResponseMessage());

			Map<String, List<String>> headerFields = connection.getHeaderFields();
			List<String> cookiesHeader = headerFields.get("Set-Cookie");
			if (cookiesHeader != null) {
				String cookie = cookiesHeader.get(0);
				HttpCookie httpCookie = HttpCookie.parse(cookie).get(0);
				String name = httpCookie.getName();
				String value = httpCookie.getValue();

				cred.setCookieRoute(name + "=" + value);
				log.debug(cred.getCookieRoute());

				String cookie2 = cookiesHeader.get(1);
				HttpCookie httpCookie2 = HttpCookie.parse(cookie2).get(0);
				String name2 = httpCookie2.getName();
				String value2 = httpCookie2.getValue();

				cred.setCookieSession(name2 + "=" + value2);
				log.debug(cred.getCookieSession());
			}

		} catch (Exception e) {
			log.error(e);
			throw new ServiceException("Failed post request to server", e);
		} finally {
			if (connection != null) {
				try {
					connection.disconnect();
				} catch (Exception ex) {
					log.error(ex);
				}
			}
		}
	}

	public void trustAllHosts() {
		try {
			TrustManager[] trustAllCerts = new TrustManager[] { new X509ExtendedTrustManager() {
				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
				}

				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] xcs, String string, Socket socket)
						throws CertificateException {

				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] xcs, String string, Socket socket)
						throws CertificateException {

				}

				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] xcs, String string, SSLEngine ssle)
						throws CertificateException {

				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] xcs, String string, SSLEngine ssle)
						throws CertificateException {

				}

			} };

			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};
			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (Exception e) {
			log.error("Error occurred", e);
		}
	}

	@Override
	public List<ServiceLayerInvoiceResponse> getTopInvoice(int nilai) {
		// TODO Auto-generated method stub
		List<ServiceLayerInvoiceResponse> invoiceResponseList = new ArrayList<>();

		log.debug("Login B1.");
		loginB1(databaseName, databaseUserName, databasePassword);
		log.debug("Finish login B1.");

		URL url;
		HttpsURLConnection connection = null;
		try {
			// Create connection
			url = new URL(BaseUrl
					+ "b1s/v1/Invoices?$select=DocEntry,DocNum,CardCode,CardName,DocType,DocTotal,DocDate&$orderby=DocTotal%20desc&$top="
					+ String.valueOf(nilai));
			log.debug(url);
			trustAllHosts();
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			connection.setRequestProperty("User-Agent", USER_AGENT);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Cookie", cred.getCookieSession() + "; " + cred.getCookieRoute());
			connection.setAllowUserInteraction(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			log.debug("code con: " + connection.getResponseCode());
			log.debug("message con: " + connection.getResponseMessage());

			StringBuffer stringBuffer = new StringBuffer();

			if (connection.getResponseCode() == 200) {
				InputStream inputStream = connection.getInputStream();
				if (inputStream == null) {
					return null;
				}
				
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line).append("\n");
                }
                if (stringBuffer.length() == 0) {
                    return null;
                }

				ObjectMapper mapper = new ObjectMapper();

				JsonNode json = mapper.readTree(stringBuffer.toString());
				log.debug(json.at("/value"));
				invoiceResponseList = mapper.readValue(json.at("/value").toString(),
						new TypeReference<List<ServiceLayerInvoiceResponse>>() {
						});
			}

		} catch (Exception e) {
			log.error(e);
			throw new ServiceException("Failed post request to server", e);
		} finally {
			if (connection != null) {
				try {
					connection.disconnect();
				} catch (Exception ex) {
					log.error(ex);
				}
			}
		}
		return invoiceResponseList;
	}

	
	public List<ServiceLayerSalesOpportunityResponse> getTopSalesOp(int nilai) {
		// TODO Auto-generated method stub
		List<ServiceLayerSalesOpportunityResponse> salesResponseList = new ArrayList<>();

		log.debug("Login B1.");
		loginB1(databaseName, databaseUserName, databasePassword);
		log.debug("Finish login B1.");

		URL url;
		HttpsURLConnection connection = null;
		try {
			// Create connection
			url = new URL(BaseUrl
					+ "b1s/v1/SalesOpportunities?$select=SequentialNo,CardCode,CustomerName,OpportunityName,WeightedSumLC,MaxLocalTotal,ClosingPercentage,CurrentStageNo&$orderby=SequentialNo%20asc&$top="
					+ String.valueOf(nilai));
			log.debug(url);
			trustAllHosts();
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			connection.setRequestProperty("User-Agent", USER_AGENT);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Cookie", cred.getCookieSession() + "; " + cred.getCookieRoute());
			connection.setAllowUserInteraction(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			log.debug("code con: " + connection.getResponseCode());
			log.debug("message con: " + connection.getResponseMessage());

			StringBuffer stringBuffer = new StringBuffer();

			if (connection.getResponseCode() == 200) {
				InputStream inputStream = connection.getInputStream();
				if (inputStream == null) {
					return null;
				}
				
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line).append("\n");
                }
                if (stringBuffer.length() == 0) {
                    return null;
                }

				ObjectMapper mapper = new ObjectMapper();

				JsonNode json = mapper.readTree(stringBuffer.toString());
				log.debug(json.at("/value"));
				salesResponseList = mapper.readValue(json.at("/value").toString(),
						new TypeReference<List<ServiceLayerSalesOpportunityResponse>>() {
						});
			}

		} catch (Exception e) {
			log.error(e);
			throw new ServiceException("Failed post request to server", e);
		} finally {
			if (connection != null) {
				try {
					connection.disconnect();
				} catch (Exception ex) {
					log.error(ex);
				}
			}
		}
		return salesResponseList;
	}

	
	public ServiceLayerStageResponse getStage(int stageNo) {
		// TODO Auto-generated method stub
		ServiceLayerStageResponse stage = new ServiceLayerStageResponse();

		
		if( cred == null || cred.getCookieSession().isEmpty()) {
			log.debug("Login B1.");
			loginB1(databaseName, databaseUserName, databasePassword);			
			log.debug("Finish login B1.");
		}


		URL url;
		HttpsURLConnection connection = null;
		try {
			// Create connection
			url = new URL(BaseUrl
					+ "b1s/v1/SalesStages("
					+ String.valueOf(stageNo) + ")");
			log.debug(url);
			trustAllHosts();
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			connection.setRequestProperty("User-Agent", USER_AGENT);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Cookie", cred.getCookieSession() + "; " + cred.getCookieRoute());
			connection.setAllowUserInteraction(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			log.debug("code con: " + connection.getResponseCode());
			log.debug("message con: " + connection.getResponseMessage());

			StringBuffer stringBuffer = new StringBuffer();

			if (connection.getResponseCode() == 200) {
				InputStream inputStream = connection.getInputStream();
				if (inputStream == null) {
					return null;
				}
				
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line).append("\n");
                }
                if (stringBuffer.length() == 0) {
                    return null;
                }

				ObjectMapper mapper = new ObjectMapper();

				JsonNode json = mapper.readTree(stringBuffer.toString());
				log.debug(json.at("/value"));
				stage = mapper.readValue(stringBuffer.toString(), ServiceLayerStageResponse.class);
			}

		} catch (Exception e) {
			log.error(e);
			throw new ServiceException("Failed post request to server", e);
		} finally {
			if (connection != null) {
				try {
					connection.disconnect();
				} catch (Exception ex) {
					log.error(ex);
				}
			}
		}
		return stage;
	}

}
