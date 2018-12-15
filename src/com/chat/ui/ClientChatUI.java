/*
 * ClientChatUI.java
 *
 * Created on __DATE__, __TIME__
 */

package com.chat.ui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.chat.bean.RequestBean;
import com.chat.bean.UserBean;
import com.chat.tool.NowTime;
import com.chat.tool.SocketFileHelp;
import com.chat.tool.SocketHelp;

/**
 *
 * @author  __USER__
 */
public class ClientChatUI extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbBg;
	private JLabel herlogo;
	private JLabel lbHerName;
    private int chooseBg=0;
    private UserBean chatWith;
    private int index=0;
    private MainClientUI parent;
    private File file=null;
    
    private JButton btChooseFile;
    private JButton btSendFile;
	/** Creates new form ClientChatUI */
	public ClientChatUI(MainClientUI parent, boolean modal,UserBean chatWith,int index) {
		super(parent, modal);
		this.parent=parent;
		this.chatWith  = chatWith;
		this.index = index;
		initComponents();
		myAddListeners();
	}

	
	public void addMsg(String msg){
		NowTime now = NowTime.getInstance();
		this.textReceive.append("("+now.getTime()+") "+msg+"\r\n");
	}
	
	public void sendFile(){
		
		class SendRun implements Runnable{
			public void run() {
				if(file!=null){
					String herIP = chatWith.getIP();
					String herPort = chatWith.getPort();
					UserBean me = parent.friendList.get(0);
					Socket socket=null;
					try {
						socket = new Socket(InetAddress.getByName(herIP),Integer.valueOf(herPort));
						SocketHelp socketHelp = new SocketHelp(socket);
						RequestBean request = new RequestBean("file", me, file.getName(), null);
						socketHelp.send(request);
						
						RequestBean re=socketHelp.receive();
						if(re.getType().equals("fileok")){
							SocketFileHelp socketFileHelp = new SocketFileHelp(socket);
							socketFileHelp.sendFile(file);
						}
						if(!socket.isClosed()){
						   socket.close();
						}
						//JOptionPane.showMessageDialog(null, herIP+" "+herPort);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (UnknownHostException e) {
						JOptionPane.showMessageDialog(null, "UnknownHostException");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		
		new Thread(new SendRun()).start();
	}
	
	public void sendText(){
		String herIP = chatWith.getIP();
		String herPort = chatWith.getPort();
		UserBean me = parent.friendList.get(0);
		String msg=this.textSend.getText();
		Socket socket=null;
		if(msg!=null&&!msg.equals("")){
			try {
				socket = new Socket(InetAddress.getByName(herIP),Integer.valueOf(herPort));
				SocketHelp socketHelp = new SocketHelp(socket);
				RequestBean request = new RequestBean("chat", me, msg, null);
				socketHelp.send(request);
			//JOptionPane.showMessageDialog(null, herIP+" "+herPort);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (UnknownHostException e) {
				JOptionPane.showMessageDialog(null, "UnknownHostException");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(socket!=null){
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        NowTime now = NowTime.getInstance();
		this.textReceive.append("("+now.getTime()+") "+"I say : "+msg+"\r\n");
		this.textSend.setText("");
	}
	public void myAddListeners(){
		this.btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//这儿还不知道怎么写
				//ok了
				onCloseWindow();
			}
		});
		
		
        this.btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//lbBg.setIcon(new ImageIcon("src\\com\\chat\\ui\\bgchat.jpg"));
				sendText();
			}
		});
        
        this.textSend.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
				  sendText();
				}
			}
		});
        
        this.btChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFile();	
			}
		});
        this.btSendFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendFile();
			}
		});
	}
	
	private void chooseFile(){
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
		JFileChooser cho = new JFileChooser();
		
		if (cho.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			this.file = cho.getSelectedFile();
		}
	}
	public void onCloseWindow(){
		MainClientUI main = (MainClientUI) parent;
		main.chatUIs[index] = null;
		this.dispose();
	}
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosing(java.awt.event.WindowEvent e) {
					onCloseWindow();
				}
				});
		jScrollPane1 = new javax.swing.JScrollPane();
		textReceive = new javax.swing.JTextArea();
		jScrollPane2 = new javax.swing.JScrollPane();
		textSend = new javax.swing.JTextArea();
		btnSend = new javax.swing.JButton();
		btnClose = new javax.swing.JButton();
		btChooseFile = new JButton();
		btSendFile = new JButton();
		
		btChooseFile.setFont(new java.awt.Font("新宋体", 0, 13));
		btChooseFile.setForeground(new Color(50, 50, 250));
		btChooseFile.setText("选择文件");
		btChooseFile.setBounds(420, 100, 88, 27);
		
		btSendFile.setFont(new java.awt.Font("新宋体", 0, 13));
		btSendFile.setForeground(new Color(50, 50, 250));
		btSendFile.setText("发送文件");
		btSendFile.setBounds(420, 150, 88, 27);
		
		
		textReceive.setFont(new java.awt.Font("新宋体", 0, 13));
		//textReceive.setEditable(false);
		textSend.setFont(new java.awt.Font("新宋体", 0, 13));
		textSend.setForeground(new Color(0, 0, 255));
		
		herlogo = new JLabel();
        herlogo.setIcon(new ImageIcon("src\\com\\chat\\logo\\"+chatWith.getLogo()+".png"));
        herlogo.setBounds(60,27 , 51, 51);
        
        lbHerName = new JLabel();
        lbHerName.setFont(new java.awt.Font("新宋体", 1, 15));
        lbHerName.setText(chatWith.getUserName());
		lbHerName.setBounds(150, 40, 40, 20);
		int labnelH=lbHerName.getHeight();
		lbHerName.setSize(100, labnelH);
		setTitle("和"+chatWith.getUserName()+"聊天中...");
		
		
		lbBg = new JLabel();
		
		if(this.chooseBg==0){
             lbBg.setIcon(new ImageIcon("src\\com\\chat\\ui\\bgchat2.jpg"));
		}
		else{
			lbBg.setIcon(new ImageIcon("src\\com\\chat\\ui\\bgchat.jpg"));
		}
        lbBg.add(this.textReceive);
        lbBg.add(this.textSend);
        lbBg.add(this.btnClose);
        lbBg.add(this.btnSend);
        lbBg.add(this.herlogo);
        lbBg.add(this.lbHerName);
        lbBg.add(this.btChooseFile);
        lbBg.add(this.btSendFile);
        lbBg.setBounds(0, 0, 530, 415);
        
        
        
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setIconImage(new javax.swing.ImageIcon(
		"src\\com\\chat\\ui\\dog_left.png").getImage());
        setResizable(false);
         //右边显示
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width)  /2-300;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height) / 6;
        setLocation(w, h);
		
		textReceive.setColumns(20);
		textReceive.setRows(5);
		jScrollPane1.setViewportView(textReceive);

		textSend.setColumns(20);
		textSend.setRows(5);
		jScrollPane2.setViewportView(textSend);

		btnSend.setFont(new java.awt.Font("新宋体", 0, 12));
		btnSend.setText("\u53d1\u9001");

		btnClose.setFont(new java.awt.Font("新宋体", 0, 12));
		btnClose.setText("\u5173\u95ed");
        
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(22, 22, 22)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																373,
																Short.MAX_VALUE)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				btnClose)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				259,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnSend))
														.addComponent(
																jScrollPane2,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																373,
																Short.MAX_VALUE))
										.addGap(125, 125, 125)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(86, 86, 86)
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												135,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(44, 44, 44)
										.addComponent(
												jScrollPane2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(btnSend)
														.addComponent(btnClose))
										.addContainerGap()));
        getContentPane().add(this.lbBg);
		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
		public void run() {
			ClientChatUI dialog = new ClientChatUI(
						new MainClientUI(null,null,null), true,new UserBean("jack", null, null, null, 2),0);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnClose;
	private javax.swing.JButton btnSend;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextArea textReceive;
	private javax.swing.JTextArea textSend;
	// End of variables declaration//GEN-END:variables

}