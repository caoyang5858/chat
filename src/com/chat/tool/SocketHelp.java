package com.chat.tool;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.chat.bean.RequestBean;

public class SocketHelp {
    private Socket socket;
    public SocketHelp(Socket socket){
    	this.socket = socket;
    }
    
    public void send(RequestBean r){
    	
    	if(r!=null){
    		try {
    			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				    out.writeObject(r);
					out.flush();
					out.reset();
				   // out.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "SocketHelp_IOIOException_1");
			}
			
    	}
    }
    
    public RequestBean receive(){
    	
    	
    	try {
			ObjectInputStream  in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			Object obj = in.readObject();
			if(obj!=null){
				RequestBean re = (RequestBean)obj;
				return re;
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "SocketHelp_IOIOException_2");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "SocketHelp_ClassNotFoundException_1");
		}
    	return null;
    }
}
