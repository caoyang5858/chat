package com.chat.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.chat.bean.RequestBean;
import com.chat.bean.UserBean;
import com.chat.tool.SocketHelp;

public class TellAllTheDisconnectUser {
    private ServerData serverData;
    private UserBean who;
	public TellAllTheDisconnectUser(ServerData serverData, UserBean who) {
		super();
		this.serverData = serverData;
		this.who = who;
	}
	
	public void tell(){
				for(int i=0;i<serverData.getOnlineListSize();i++){
					String tmpIp=serverData.getOnlineListUser(i).getIP();
					String tmpPort = serverData.getOnlineListUser(i).getPort();
					try {
						Socket socket = new Socket(InetAddress.getByName(tmpIp),Integer.valueOf(tmpPort));
						SocketHelp socketHelp = new SocketHelp(socket);
						RequestBean re = new RequestBean("downline", who, null, null);
						socketHelp.send(re);
						socket.close();
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "tellCLient_NumberFormatException");
					} catch (UnknownHostException e) {
						JOptionPane.showMessageDialog(null, "tellCLient_UnknownHostException");
					} catch (IOException e) {
					//	JOptionPane.showMessageDialog(null, "tellCLientDisconnected_IO");
					}
				}
	}
    
}

