package com.chat.server;
import java.util.ArrayList;
import com.chat.bean.UserBean;

public class ServerData{
	private ArrayList<UserBean> loginList;
    private ArrayList<UserBean> onlineList;
    private boolean[] testOnline ;
    public ServerData(){
    	loginList = new ArrayList<UserBean>();
    	onlineList = new ArrayList<UserBean>();
    	testOnline = new boolean[40];//最多40人线上
    	for(int i=0;i<40;i++){
    		testOnline[i] = true;
    	}
    
    }
    public synchronized boolean addLoginList(UserBean u){
    	return loginList.add(u);
    }
    public synchronized  boolean addOnlineList(UserBean u){
    	return onlineList.add(u);
    }
    public synchronized void setTestOnline(int i,boolean val){
    	if(i>=testOnline.length){
    		return ;
    	}
    	testOnline[i] = val;
    }
    
    public synchronized boolean delOnlineList(UserBean u){
    	return onlineList.remove(u);
    }
    public synchronized boolean delOnlineList(UserBean u,int i){
    	testOnline[i] = true;
    	for(int j=i;j<40;j++){
    		testOnline[j] =testOnline[j+1];
    	}
    	return onlineList.remove(u);
    }
    public UserBean getLoginListUser(int i){
    	if(i>=loginList.size()){
    		return null;
    	}
    	return loginList.get(i);
    }
    
    public UserBean getOnlineListUser(int i){
    	if(i>=onlineList.size()){
    		return null;
    	}
    	return onlineList.get(i);
    }
    
    public boolean getTestOnline(int i){
    	if(i>=testOnline.length){
    		return false;
    	}
    	return testOnline[i];
    }
    
    public int getLoginListSize(){
    	return this.loginList.size();
    }
    
    public int getOnlineListSize(){
    	return this.onlineList.size();
    }
}