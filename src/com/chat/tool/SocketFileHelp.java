package com.chat.tool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketFileHelp {
    private Socket socket;
    public SocketFileHelp(Socket sock){
    	this.socket  = sock;
    }
    public boolean getFile(File outfile){
    	
		try {
			InputStream is = socket.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			OutputStream os = new FileOutputStream(outfile);
			BufferedOutputStream bos = new BufferedOutputStream(os);

			byte[] cache = new byte[1024 * 1024];
			int len = -1;

			while ((len = bis.read(cache)) != -1) {
				bos.write(cache, 0, len);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}

			bos.close();
			os.close();
			bis.close();
			is.close();
			socket.close();
		} catch (IOException e) {
            
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	public boolean sendFile(File file){
		if (file != null) {
			InputStream is;
			try {
				is = new FileInputStream(file);
				BufferedInputStream bis = new BufferedInputStream(is);

				
				OutputStream os = socket.getOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(os);
				
				
				
				byte[] cache = new byte[1024 * 1024];
				int len = -1;
				while ((len = bis.read(cache)) != -1) {
				
					bos.write(cache, 0, len);
				}

				bis.close();
				is.close();
				bos.close();
				os.close();
				socket.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return false;
			} catch (UnknownHostException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return false;
	}
	
}
