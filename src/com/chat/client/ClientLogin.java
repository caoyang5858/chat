package com.chat.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import com.chat.bean.RequestBean;
import com.chat.bean.UserBean;

public class ClientLogin {
       private String userName;
       private String pwd;
       private Socket socket;
       private ObjectOutputStream out;
       private ObjectInputStream in;
    
       
       ArrayList<UserBean> list=null;
       /**
        * ʵ�ֵ�½
        * @param userName
        * @param pwd
        * @param soket,�����ѽ���socket,��������������request
        */
	public ClientLogin(String userName, String pwd, Socket soket) {
		super();
		this.userName = userName;
		this.pwd = pwd;
		this.socket = soket;
		
	}
	/**
	 * 
	 * @return ������ط�null,˵����½�ɹ�,���غ���list
	 */
	public ArrayList<UserBean> login(){
		UserBean me = new UserBean(userName, pwd, null, null,0);
		RequestBean request = new RequestBean("login", me, null, null);
		
		
		
		  
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(request);
			out.flush();
			//out.reset();
			//out.close();//������ܹ�,һ�ؾ�����socket������
			
			//socket.setSoTimeout(8000);
			new Thread(new Runnable() {
				
				public void run() {
					Object obj=null;
					try {
						in = new ObjectInputStream(socket.getInputStream());
						obj = in.readObject();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "ClientLogin_IOException_thread");
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(null, "ClientLogin_ClassNotFoundException");
						obj=null;
					}
					//in.reset();
				    //in.close();
				   if(obj!=null){
                      
					   RequestBean re = (RequestBean)obj;
					   if(re.getType().equals("login_ok")){
						   list=re.getList();
					   }
					   else if(re.getType().equals("haslogin")){
						   JOptionPane.showMessageDialog(null,"��½ʧ��,����˺��Ѿ���¼");
					   }
					   else{
						   JOptionPane.showMessageDialog(null,"��½ʧ��,��������ȷ���û���������");
					   }
				   }
				}
			}).start();
			
			Thread.sleep(2000);
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ClientLogin_IOException");
			e.printStackTrace();
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, "ClientLogin_InterruptedException");
		}
		
		if(list!=null){
			return list;
		}
		return null;
	}
       
}





