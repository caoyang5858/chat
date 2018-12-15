package com.chat.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.chat.bean.RequestBean;
import com.chat.bean.UserBean;
import com.chat.tool.SocketHelp;

public class TellAllClientButMe {
    private ServerData serverData;
    private String type;
    private UserBean me;
    private String msg;
    public TellAllClientButMe(ServerData serverData, String type, UserBean me,String msg) {
		super();
		this.serverData = serverData;
		this.type = type;
		this.me = me;
		this.msg = msg;
	}
	
	public void tell(){
		ImpRunForGroup  runs[] = new ImpRunForGroup[serverData.getOnlineListSize()];
		       System.out.println(" group: "+me.getUserName()+" "+type);
		        int j=0;
				for(int i=0;i<serverData.getOnlineListSize();i++){
					 if( serverData.getOnlineListUser(i).getUserName().equals(me.getUserName())){
						 continue;//跳过获取的信息燃火发送
					 }
					 runs[j++] = new ImpRunForGroup(i,serverData,me,msg,type);
				}
				for(int i=0;i<runs.length;i++){
					new Thread(runs[i]).start();
				}
				 System.out.println("  group: "+me.getUserName()+" "+type+"  OK!");
	}
	
}


class ImpRunForGroup implements Runnable{
	public int index=0;
	private ServerData serverData;
	private String msg;
	private String type;
	private UserBean me;
	public ImpRunForGroup(int i,ServerData serverData,UserBean me,String msg,String type){
		this.index = i;
		this.serverData = serverData;
		this.msg = msg;
		this.type = type;
		this.me = me;
	}
	public void run() {
		String tmpIp=serverData.getOnlineListUser(index).getIP();
		String tmpPort = serverData.getOnlineListUser(index).getPort();
		try {
			Socket socket = new Socket(InetAddress.getByName(tmpIp),Integer.valueOf(tmpPort));
			SocketHelp socketHelp = new SocketHelp(socket);
			RequestBean re = new RequestBean(type, me, msg, null);
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
			//System.out.println();
		}
	}
}