package com.chat.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.chat.bean.RequestBean;
import com.chat.bean.UserBean;
import com.chat.tool.SocketHelp;

public class TellAllClient {
    private ServerData serverData;
    private String type;
    private UserBean who;
    public TellAllClient(ServerData serverData, String type, UserBean who) {
		super();
		this.serverData = serverData;
		this.type = type;
		this.who = who;
	}
	
	public void tell(){
		      ImpRun  runs[] = new ImpRun[serverData.getOnlineListSize()];
		       System.out.println(" tell all "+who.getUserName()+" "+type);
				for(int i=0;i<serverData.getOnlineListSize();i++){
					runs[i] = new ImpRun(i,serverData,who,type);
					
				}
				for(int i=0;i<serverData.getOnlineListSize();i++){
					new Thread(runs[i]).start();
				}
				 System.out.println(" tell all "+who.getUserName()+" "+type+"  OK!");
	}
	
}


class ImpRun implements Runnable{
	public int index=0;
	private ServerData serverData;
	private UserBean who;
	private String type;
	public ImpRun(int i,ServerData serverData,UserBean who,String type){
		this.index = i;
		this.serverData = serverData;
		this.who = who;
		this.type = type;
	}
	public void run() {
		String tmpIp=serverData.getOnlineListUser(index).getIP();
		String tmpPort = serverData.getOnlineListUser(index).getPort();
		try {
			Socket socket = new Socket(InetAddress.getByName(tmpIp),Integer.valueOf(tmpPort));
			SocketHelp socketHelp = new SocketHelp(socket);
			RequestBean re = new RequestBean(type, who, null, null);
			socketHelp.send(re);
			socket.close();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "tellCLient_NumberFormatException");
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "tellCLient_UnknownHostException");
		} catch (IOException e) {
			//如果到这个客户机不能建立连接，说明他断开了连接，将他删除，并 通知其他人
           // serverData.setTestOnline(i, false);//说明第i个人下线了
			//JOptionPane.showMessageDialog(null, "tellCLient_IO");
		}
	}
}