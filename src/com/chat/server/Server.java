package com.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.chat.bean.RequestBean;
import com.chat.bean.UserBean;
import com.chat.tool.SocketHelp;

public class Server implements Runnable{
    private ServerSocket ss;
    private boolean run=true;
    private ServerData serverdata;
    public Server(){
    	
    	serverdata = new ServerData();
//    	serverdata.addLoginList(new UserBean("jack","dd",null,null,1));
//    	serverdata.addLoginList(new UserBean("jack2","dd",null,null,2));
//    	serverdata.addLoginList(new UserBean("jack3","dd",null,null,3));
//    	serverdata.addOnlineList(new UserBean("peter","dd","192.1.1.0","60",2));
//    	serverdata.addOnlineList(new UserBean("peter1","dd","192.1.1.0","60",3));
//    	serverdata.addOnlineList(new UserBean("peter2","dd","192.1.1.0","60",4));
//    	serverdata.addOnlineList(new UserBean("peter3","dd","192.1.1.0","60",5));
    	try {
			ss = new ServerSocket(8005);
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "服务器创建失败");
		}
		
//		new Thread(new Runnable() {//每隔8秒,通知有用户下线,是否有人下线资源来自 TellAllClient的失败
//			public void run() {
//				while(true){
//					for(int i=0;i<40;i++){
//						if(serverdata.getTestOnline(i)==false){
//							UserBean downUser = serverdata.getOnlineListUser(i);
//							serverdata.delOnlineList(serverdata.getOnlineListUser(i));		
//							System.out.println("Onlie num:"+serverdata.getOnlineListSize()+",downline:"+downUser.getUserName());
//							TellAllTheDisconnectUser tell = new TellAllTheDisconnectUser(serverdata, downUser);
//							tell.tell();
//						}
//						
//					}
//
//					try {
//						Thread.sleep(8000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}).start();
//		
		
    }
    
    
    public ServerData getServerData(){
    	return serverdata;
    }
    
    public void run() {
    	while(run){
    		try {
				Socket socket=ss.accept();
				ServeThread runnable = new ServeThread(this,socket);
				Thread t = new Thread(runnable);
				t.start();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "IOIOException");
			}
			
    	}
	}
    public void serve(){
    	run();
    }
    
    public void startServer(){
    	if(ss==null){
    		try {
				ss = new ServerSocket(8005);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	this.run = true;
    }
    public void stopServer(){
    	try {
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ss=null;
    	this.run = false;
    }
    
    public  static void main(String args[]){
    	new Server().serve();
    }
	
}



class ServeThread implements Runnable{
    private Server server;
    private Socket socket;
	public ServeThread(Server server,Socket socket){
		this.server = server;
		this.socket = socket;
	}
	public void run() {
		
		  SocketHelp socketHelp = new SocketHelp(socket);
		  RequestBean request= socketHelp.receive();
		  
		  if(request.getType().equals("login")){
			  String isLoginOK="login_failed";
			  ArrayList<UserBean> list = null;
			   UserBean userFormLoginList=null;
			   boolean hasLogin=false;
			  
			   for(int i=0;i<server.getServerData().getOnlineListSize();i++){
				   String name = server.getServerData().getOnlineListUser(i).getUserName();
				   if(request.getUser().getUserName().equals(name)){
					   hasLogin=true;
					   RequestBean re= new RequestBean("haslogin", null, null, list);
					   socketHelp.send(re);//回复客户端
					   break;
				   }
			   }
			   if(!hasLogin){
				   for(int i=0;i<server.getServerData().getLoginListSize();i++){
					   String tmp=server.getServerData().getLoginListUser(i).getUserName();
					   String pwd=server.getServerData().getLoginListUser(i).getPwd();
					   if(request.getUser().getUserName().equals(tmp)&&request.getUser().getPwd().equals(pwd)){
						   //可以登录
						   isLoginOK="login_ok";
						   list = new ArrayList<UserBean>();
						   userFormLoginList=server.getServerData().getLoginListUser(i);//方便下面加入onlineList的时候使用
						   list.add(server.getServerData().getLoginListUser(i));//加入自己到返回列表
						   break;
					   }
				   }
				   if(list!=null){
					   for(int i=0;i<server.getServerData().getOnlineListSize();i++){
						   list.add(server.getServerData().getOnlineListUser(i));
					   }
				   }
				   if(isLoginOK.equals("login_ok")){	  
					   UserBean userbean = new UserBean(userFormLoginList.getUserName(), userFormLoginList.getPwd(), 
							   socket.getInetAddress().getHostAddress(), new String(""+socket.getPort()), userFormLoginList.getLogo());
					   System.out.println(userbean.getUserName()+socket.getInetAddress().getHostAddress()+" online");

					   RequestBean re= new RequestBean(isLoginOK, null, null, list);
					   socketHelp.send(re);//回复客户端

					   TellAllClient tell = new TellAllClient(server.getServerData(), "online", userbean);
					   tell.tell();//告诉其他好友  user上线	 
					  
					   server.getServerData().addOnlineList(userbean);
				   }
				   else{
					   RequestBean re= new RequestBean(isLoginOK, null, null, list);
					   socketHelp.send(re);//回复客户端
				   }
			   }
			  
		  }
		  else if(request.getType().equals("register")){
			  if(request.getUser()!=null){
				  boolean permit=true;
				  String registerUser = request.getUser().getUserName();
				  for(int i=0;i<server.getServerData().getLoginListSize();i++){
					  String tmp = server.getServerData().getLoginListUser(i).getUserName();
					  if(registerUser.equals(tmp)){
						  permit=false;
					  }
				  }
				  if(permit){
					  if(server.getServerData().addLoginList(request.getUser())){
						  RequestBean re= new RequestBean("register_ok", null, null, null);
						  socketHelp.send(re);
					  }
				  }
				  else{
					  RequestBean re= new RequestBean("register_fail", null, null, null);
					  socketHelp.send(re);
				  }
			  }
			  else{
				  RequestBean re= new RequestBean("register_wrong", null, null, null);
			      socketHelp.send(re);
			  }
			  
		  }
		  else if(request.getType().equals("exit")){
			  
			  UserBean user =request.getUser();
			  if(user!=null){
				  for(int i=0;i<server.getServerData().getOnlineListSize();i++){
					  String tmp = server.getServerData().getOnlineListUser(i).getUserName();
					  if(user.getUserName().equals(tmp)){
						 boolean bb=server.getServerData().delOnlineList(server.getServerData().getOnlineListUser(i));
						 System.out.println(tmp+" 下线 "+bb); 
						  //通知其他在线用户他下了
						 TellAllClient tell = new TellAllClient(server.getServerData(), "downline", user);
						 tell.tell();
					  }
				  }
			  }
		  }
		  else if(request.getType().equals("group")){
			  String msg = request.getMsg();
			  UserBean me = request.getUser();
			  TellAllClientButMe tell = new TellAllClientButMe(server.getServerData(), "broadcast", me, msg);
			  tell.tell();
		  }
		  try {
			  socket.close();
		  } catch (IOException e) {
			  JOptionPane.showMessageDialog(null, "ServerThread_IOException");
		  }
		
	}
	
}




