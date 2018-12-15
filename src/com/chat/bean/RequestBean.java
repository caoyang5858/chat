package com.chat.bean;
import java.io.Serializable;
import java.util.ArrayList;

public class RequestBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
private String  type;
   private UserBean user;
   private String  msg;
   private ArrayList<UserBean> list;
 
   /**
    * 这是一个发送的请求bean
    * @param type
    * @param user,请求的人
    * @param msg
    * @param list ,//返回需要的信息
    */
public RequestBean(String type, UserBean user, String msg,
		ArrayList<UserBean> list) {
	super();
	this.type = type;
	this.user = user;
	this.msg = msg;
	this.list = list;
}


public UserBean getUser() {
	return user;
}


public void setUser(UserBean user) {
	this.user = user;
}


public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public ArrayList<UserBean> getList() {
	return list;
}
public void setList(ArrayList<UserBean> list) {
	this.list = list;
}
   
}
