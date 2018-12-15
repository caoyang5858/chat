package com.chat.client;

import java.io.File;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.chat.bean.RequestBean;
import com.chat.bean.UserBean;
import com.chat.tool.SocketFileHelp;
import com.chat.tool.SocketHelp;
import com.chat.ui.ClientChatUI;
import com.chat.ui.GroupChatUI;
import com.chat.ui.MainClientUI;

public class ClientRecv {
	private MainClientUI mainClient;
	private ServerSocket recvSocket;
	private boolean run=true;
	public ClientRecv(MainClientUI mainClient, ServerSocket recvSocket) {
		this.mainClient = mainClient;
		this.recvSocket = recvSocket;
	}
	public void receive(){
		new Thread(new Runnable() {
			public void run() {
				while(run){
					try {
						//JOptionPane.showMessageDialog(null, "one");
						Socket socket=recvSocket.accept();
						//JOptionPane.showMessageDialog(null, "two");
						RecvThread rnb=new RecvThread(mainClient, socket);
						new Thread(rnb).start();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}
    
	public void stop(){
		run=false;
	}
}


class RecvThread implements Runnable{
	private MainClientUI mainClient;
	private Socket socket;
	public RecvThread(MainClientUI mainClient, Socket socket) {
		super();
		this.mainClient = mainClient;
		this.socket = socket;
	}
	public void run() {
		
		//JOptionPane.showMessageDialog(null, "reveive1");
		SocketHelp socketHelp = new SocketHelp(socket);
		RequestBean request= socketHelp.receive();
		
		if(request.getType().equals("chat")){
			
			String msg = request.getMsg();
			UserBean chatWith=request.getUser();//发消息的人
			int index=0;
			//由于没有通知 ，所以找不到,上线下线通知,下面的逻辑需要加上上线下线通知
			for(int i=0;i<mainClient.friendList.size();i++){
				String tmpName = mainClient.friendList.get(i).getUserName();
				if(tmpName.equals(chatWith.getUserName())){
					index = i;//发消息的人在列表的i位置
					break;
				}
			}
			//JOptionPane.showMessageDialog(null, "reveive2"+chatWith.getUserName());
			if(index>0){
				//JOptionPane.showMessageDialog(null, "reveive3");
				 if(mainClient.chatUIs[index]==null){//为和对方聊天开辟窗口，对方的消息是本地的，来自服务器
					 mainClient.chatUIs[index]=new ClientChatUI(mainClient, false,mainClient.friendList.get(index),index);
				 }
				 mainClient.chatUIs[index].setVisible(true);
				 mainClient.chatUIs[index].addMsg(chatWith.getUserName()+" : "+msg);
			}
		}
		else if(request.getType().equals("downline")){
			UserBean who = request.getUser();//服务器发过来的,通知谁下线了
			//JOptionPane.showMessageDialog(null, who.getUserName()+"downLine");
			int index=0;
			for(int i=0;i<mainClient.friendList.size();i++){
				String tmpName = mainClient.friendList.get(i).getUserName();
				if(tmpName.equals(who.getUserName())){
					index = i;//下线的人在列表的i位置
					break;
				}
			}
			if(index!=0){
				mainClient.friendList.remove(index);
				mainClient.removeFriendFromTree(index);
			}
			
		}
        else if(request.getType().equals("online")){
        	UserBean who = request.getUser();//服务器发过来的,通知谁上线了
   
        	mainClient.friendList.add(who);
        	int i=mainClient.friendList.size()-1;
        	mainClient.addFriendFromTree(who, i);
        	//JOptionPane.showMessageDialog(null, who.getUserName()+"online");
        	
		}
        else if(request.getType().equals("broadcast")){
        	 String msg = request.getMsg();
        	 String who = request.getUser().getUserName();
        	 if(mainClient.groupChatUI!=null){
        		 mainClient.groupChatUI.addMsg(who +" : "+msg);
        	 }
        	 else{
        		 mainClient.groupChatUI = new GroupChatUI(mainClient,false,mainClient.getServerIP(),mainClient.getServerPort());
        		 mainClient.groupChatUI.setVisible(true);
        		 mainClient.groupChatUI.addMsg(who +" : "+msg);
        	 }
        }
        else if(request.getType().equals("file")){
        	try {
    			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    		} catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (InstantiationException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IllegalAccessException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (UnsupportedLookAndFeelException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
        	String msg = request.getMsg();
       	    String who = request.getUser().getUserName();
       	    int re = JOptionPane.showConfirmDialog(null, "你要接收"+who+"的"+msg+"文件么");
       	    if(re==JOptionPane.OK_OPTION){
       	    	JFileChooser fileChooser = new JFileChooser("D:\\");
       	    	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
       	        int returnVal = fileChooser.showSaveDialog(mainClient);
       	        System.out.println("aaaaaaaaaaaaaaa");
       	        if(returnVal == JFileChooser.APPROVE_OPTION){       
       	        	String filePath= fileChooser.getSelectedFile().getAbsolutePath();//这个就是你选择的文件夹的路径

       	        	RequestBean reFile  = new RequestBean("fileok", null, null, null);
       	        	socketHelp.send(reFile);

       	        	File outfile = new File(filePath+msg);

       	        	SocketFileHelp socketFileHelp = new SocketFileHelp(socket);
       	        	socketFileHelp.getFile(outfile);
       	        	System.out.println("bbbbbbbbbbbb");
               	  
               	  
       	    	}
       	        else{
       	        	RequestBean reFile  = new RequestBean("fileno", null, null, null);
           	    	socketHelp.send(reFile);
       	        }
       	       System.out.println("cccccccccccccc");
       	    }
       	    else{
       	    	RequestBean reFile  = new RequestBean("fileno", null, null, null);
       	    	socketHelp.send(reFile);
       	    }
        }
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
