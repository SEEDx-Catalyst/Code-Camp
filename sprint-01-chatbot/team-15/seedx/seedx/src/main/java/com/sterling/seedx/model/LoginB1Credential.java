package com.sterling.seedx.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginB1Credential {
	private String CompanyDB;
	private String Password;
	private String UserName;
	
	private String cookieRoute;
	private String cookieSession;
	
	public LoginB1Credential(){
		this.CompanyDB = "";
		this.Password = "";
		this.UserName = "";
		this.cookieRoute="";
		this.cookieSession="";
	}
	
	public LoginB1Credential(String CompanyDB, String Password, String UserName, String cookieRoute, String cookieSession){
		this.CompanyDB = CompanyDB;
		this.Password = Password;
		this.UserName = UserName;
		this.cookieRoute =  cookieRoute;
		this.cookieSession =  cookieSession;
	}

	@JsonProperty("CompanyDB")
	public String getCompanyDB() {
		return CompanyDB;
	}

	public void setCompanyDB(String companyDB) {
		CompanyDB = companyDB;
	}

	@JsonProperty("Password")
	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	@JsonProperty("UserName")
	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getCookieRoute() {
		return cookieRoute;
	}

	public void setCookieRoute(String cookieRoute) {
		this.cookieRoute = cookieRoute;
	}

	public String getCookieSession() {
		return cookieSession;
	}

	public void setCookieSession(String cookieSession) {
		this.cookieSession = cookieSession;
	}
	
}
