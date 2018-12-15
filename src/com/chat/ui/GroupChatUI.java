/*
 * GruopChatUI.java
 *
 * Created on __DATE__, __TIME__
 */

package com.chat.ui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.chat.bean.RequestBean;
import com.chat.tool.NowTime;
import com.chat.tool.SocketHelp;

/**
 *
 * @author  __USER__
 */
public class GroupChatUI extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Creates new form GruopChatUI */
	private JLabel lbBg;
    private String serverIP;
    private String serverPort;
    private MainClientUI main;
    /**
     * 
     * @param parent
     * @param modal
     * @param serverIP
     * @param serverPort
     */
	public GroupChatUI(MainClientUI parent, boolean modal,String serverIP,String serverPort) {
		super(parent, modal);
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		this.main = parent;
		initComponents();
		addListeners();
	}

	public void addListeners(){
		this.btClose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onCloseWindow();
			}
		});
		
		this.btSend.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
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
	}
	
	public void addMsg(String msg){
		NowTime now = NowTime.getInstance();
		this.textReceive.append("("+now.getTime()+") "+msg+"\r\n");
	
	}
	
	public void sendText(){
		Socket socket=null;
		String msg = this.textSend.getText();
		if(!msg.equals("")){
			try {
				socket = new Socket(InetAddress.getByName(serverIP),Integer.valueOf(serverPort));
				SocketHelp socketHelp = new SocketHelp(socket);

				RequestBean re = new RequestBean("group", main.friendList.get(0), msg, null);
				socketHelp.send(re);
				socket.close();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			NowTime now = NowTime.getInstance();
			this.textReceive.append("("+now.getTime()+") "+"I say : "+msg+"\r\n");
		}
		this.textSend.setText("");
	}
	
	public void onCloseWindow(){
		main.groupChatUI=null;
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
		jScrollPane2 = new javax.swing.JScrollPane();
		textReceive = new javax.swing.JTextArea();
		btClose = new javax.swing.JButton();
		btSend = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		textSend = new javax.swing.JTextArea();

		this.lbBg = new JLabel();
		lbBg.setIcon(new ImageIcon("src\\com\\chat\\ui\\bgchat.jpg"));
		lbBg.add(this.textReceive);
		lbBg.add(this.textSend);
		lbBg.add(this.btClose);
		lbBg.add(this.btSend);
		lbBg.setBounds(0, 0, 500, 450);
		
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setIconImage(new javax.swing.ImageIcon(
		"src\\com\\chat\\ui\\dog_left.png").getImage());
        setResizable(false);
         //�ұ���ʾ
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width)  /2-300;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height) / 6;
        setLocation(w, h);
        setTitle("Ⱥ��");
		
		
		
		
		textReceive.setColumns(20);
		textReceive.setRows(5);
		jScrollPane2.setViewportView(textReceive);

		btClose.setFont(new java.awt.Font("������", 0, 12));
		btClose.setText("\u5173\u95ed");
		

		btSend.setFont(new java.awt.Font("������", 0, 12));
		btSend.setText("\u53d1\u9001");
		

		textSend.setColumns(20);
		textSend.setRows(5);
		jScrollPane1.setViewportView(textSend);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addGap(49, 49, 49)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																323,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																layout
																		.createSequentialGroup()
																		.addComponent(
																				btClose)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				209,
																				Short.MAX_VALUE)
																		.addComponent(
																				btSend))
														.addComponent(
																jScrollPane2,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																323,
																Short.MAX_VALUE))
										.addGap(58, 58, 58)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addGap(22, 22, 22).addComponent(
						jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE,
						154, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(38,
						38, 38).addComponent(jScrollPane1,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE).addGap(27, 27,
						27).addGroup(
						layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btClose).addComponent(btSend))
						.addContainerGap(13, Short.MAX_VALUE)));

		getContentPane().add(lbBg);
		pack();
	}// </editor-fold>
	//GEN-END:initComponents


	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				GroupChatUI dialog = new GroupChatUI(null,
						true,null,null);
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
	private javax.swing.JButton btClose;
	private javax.swing.JButton btSend;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextArea textSend;
	private javax.swing.JTextArea textReceive;
	// End of variables declaration//GEN-END:variables

}