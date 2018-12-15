package com.chat.bean;

public class NodeInformation {
	private int logoType;
	private int index;
	private String userName;
	/**
	 * 
	 * @param logoType 
	 * @param index 
	 * @param userName
	 */
	public NodeInformation(int logoType, int index, String userName) {
		super();
		this.logoType = logoType;
		this.index = index;
		this.userName = userName;
	}

	public int getLogoType() {
		return logoType;
	}

	public void setLogoType(int logoType) {
		this.logoType = logoType;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return userName;
	}

}
