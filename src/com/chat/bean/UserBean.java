package com.chat.bean;

import java.io.Serializable;

public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String userName;
    private String pwd;
    private String IP;
    private String port;
    private int logo;
    /**
     * 这是一个用户bean
     * @param userName
     * @param pwd
     * @param iP
     * @param port
     */
	public UserBean(String userName, String pwd, String iP, String port,int logo) {
		super();
		this.userName = userName;
		this.pwd = pwd;
		this.IP = iP;
		this.port = port;
		this.logo=logo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public int getLogo() {
		return logo;
	}
	public void setLogo(int logo) {
		this.logo = logo;
	}
	
    
}
