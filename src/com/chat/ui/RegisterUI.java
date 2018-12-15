/*
 * RegisterUI.java
 *
 * Created on __DATE__, __TIME__
 */

package com.chat.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

import com.chat.bean.RequestBean;
import com.chat.bean.UserBean;
import com.chat.tool.ComplexCellRenderer;
import com.chat.tool.SocketHelp;

/**
 *
 * @author  __USER__
 */
public class RegisterUI extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JLabel lbBg;

	
    private Socket socket;
	private String IP="127.0.0.1";//������ IP
	private String port="8005";//�������˿�
	/** Creates new form RegisterUI */
	public RegisterUI(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		textUserName = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		textPwd = new javax.swing.JPasswordField();
		textConfirmPwd = new javax.swing.JPasswordField();
		jComboBox1 = new javax.swing.JComboBox();
		jLabel4 = new javax.swing.JLabel();
		jbtOK = new javax.swing.JButton();
		jbtReset = new javax.swing.JButton();
       
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setIconImage(new javax.swing.ImageIcon(
		"src\\com\\chat\\ui\\dog_left.png").getImage());
        setResizable(false);//ȡ�����
        //�ұ���ʾ
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width)  /2+100;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height) / 6;
        setLocation(w, h);
        setTitle("ע��");
        

		jLabel1.setFont(new java.awt.Font("������", 0, 12));
		jLabel1.setText("\u7528 \u6237 \u540d \uff1a");

		jLabel2.setFont(new java.awt.Font("������", 0, 12));
		jLabel2.setText("\u5bc6    \u7801 \uff1a");

		jLabel3.setFont(new java.awt.Font("������", 0, 12));
		jLabel3.setText("\u786e\u8ba4\u5bc6\u7801 \uff1a");

		Object elements[][] = {
				{new Font("������", 1, 20), Color.BLACK, new ImageIcon("src\\com\\chat\\logo\\1.png"), "1" },
				{new Font("������", 1, 20), Color.BLACK, new ImageIcon("src\\com\\chat\\logo\\2.png"), "2" },
				{new Font("������", 1, 20), Color.BLACK, new ImageIcon("src\\com\\chat\\logo\\3.png"), "3" },
				{new Font("������", 1, 20), Color.BLACK, new ImageIcon("src\\com\\chat\\logo\\4.png"), "4" },
				{new Font("������", 1, 20), Color.BLACK, new ImageIcon("src\\com\\chat\\logo\\5.png"), "5" },
				{new Font("������", 1, 20), Color.BLACK, new ImageIcon("src\\com\\chat\\logo\\6.png"), "6" },
				{new Font("������", 1, 20), Color.BLACK, new ImageIcon("src\\com\\chat\\logo\\7.png"), "7" },
				{new Font("������", 1, 20), Color.BLACK, new ImageIcon("src\\com\\chat\\logo\\8.png"), "8" },
				{new Font("������", 1, 20), Color.BLACK, new ImageIcon("src\\com\\chat\\logo\\9.png"), "9" },
				{new Font("������", 1, 20), Color.BLACK, new ImageIcon("src\\com\\chat\\logo\\10.png"), "10" },
				{new Font("������", 1, 20), Color.BLACK, new ImageIcon("src\\com\\chat\\logo\\11.png"), "11" }
		};
		ListCellRenderer renderer = new ComplexCellRenderer();      
		jComboBox1 = new JComboBox(elements);
		jComboBox1.setRenderer(renderer); 
		
		jbtOK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtOKActionPerformed(evt);
			}
		});
		jbtReset.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtResetActionPerformed(evt);
			}
		});

		jLabel4.setFont(new java.awt.Font("������", 0, 12));
		jLabel4.setText("\u9009\u62e9\u5934\u50cf \uff1a");

		jbtOK.setFont(new java.awt.Font("������", 0, 12));
		jbtOK.setText("\u786e\u5b9a");

		jbtReset.setFont(new java.awt.Font("������", 0, 12));
		jbtReset.setText("\u91cd\u7f6e");

		lbBg = new JLabel();
		lbBg.setIcon(new ImageIcon("src\\com\\chat\\ui\\bg.jpg"));
		lbBg.add(this.jbtOK);
		lbBg.add(this.jbtReset);
		lbBg.add(this.jComboBox1);
		lbBg.add(this.jLabel1);
		lbBg.add(this.jLabel2);
		lbBg.add(this.jLabel3);
		lbBg.add(this.jLabel4);
		lbBg.add(this.textConfirmPwd);
		lbBg.add(this.textPwd);
		lbBg.add(this.textUserName);
		lbBg.setBounds(0, 0, 400,350);
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
										.addGap(62, 62, 62)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jbtReset)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				83,
																				Short.MAX_VALUE)
																		.addComponent(
																				jbtOK)
																		.addGap(
																				94,
																				94,
																				94))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addGroup(
																								layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel2)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												textPwd))
																						.addGroup(
																								layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel1)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												textUserName,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												99,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								layout
																										.createSequentialGroup()
																										.addGroup(
																												layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jLabel3)
																														.addComponent(
																																jLabel4))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addGroup(
																												layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																textConfirmPwd)
																														.addComponent(
																																jComboBox1,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE))))
																		.addContainerGap(
																				122,
																				Short.MAX_VALUE)))));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(31, 31, 31)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabel1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																15,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																textUserName,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																textPwd,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3)
														.addComponent(
																textConfirmPwd,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(59, 59, 59)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(
																jComboBox1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(41, 41, 41)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jbtOK)
														.addComponent(jbtReset))
										.addContainerGap(26, Short.MAX_VALUE)));
        getContentPane().add(this.lbBg);
		pack();
	}// </editor-fold>
	//GEN-END:initComponents
    
	private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		//����Ϸ��ж�
		String pwd = new String(this.textConfirmPwd.getPassword());
		String pwd2 = new String(this.textPwd.getPassword());
		String userName = this.textUserName.getText();
		if(!pwd.equals("")&&!pwd2.equals("")&&!userName.equals("")){
			if(pwd.equals(pwd2)){
				
				  Socket s =this.doCreateSocket();
				  if(s!=null){
					  int logo = this.jComboBox1.getSelectedIndex()+1;
					  UserBean userbean = new UserBean(userName, pwd, null, null, logo);
					  RequestBean re = new RequestBean("register", userbean, null, null);
					  SocketHelp socketHelp = new SocketHelp(s);
					  socketHelp.send(re);
					  RequestBean reFromServer = socketHelp.receive();
					  if(reFromServer.getType().equals("register_ok")){
						  JOptionPane.showMessageDialog(null,"ע��ɹ���");
					  }
					  else if(reFromServer.getType().equals("register_fail")){
						  JOptionPane.showMessageDialog(null,"�û����ѱ�ʹ�ã�");
					  }
					  else{
						  JOptionPane.showMessageDialog(null,"ע��ʧ�ܣ�");
					  }
					  
					  try {
						s.close();
					  } catch (IOException e) {
						  JOptionPane.showMessageDialog(null,"s.close()");
					 }
				  }
				  
			}
			else{
				JOptionPane.showMessageDialog(null,"��������������");
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"����д����");
		}
	}
	
	private void jbtResetActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		//����
		this.textConfirmPwd.setText("");
		this.textPwd.setText("");
		this.textUserName.setText("");
		
	}

	private Socket doCreateSocket(){
    	if(IP!=null&&port!=null){
			try {			
			    socket = new Socket();
			   // socket.bind(new InetSocketAddress(8004));//��socket�����ض˿�8004
				socket.connect(new InetSocketAddress(InetAddress.getByName(IP),Integer.valueOf(port)),3000);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null,"�˿ڱ���������");
			} catch (UnknownHostException e1) {
				JOptionPane.showMessageDialog(null,"UnKownHostException");
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,"������û��");
			}
		}
		
		if(socket.isConnected()&&!socket.isClosed()){
			return socket;
		}
		else{
			JOptionPane.showMessageDialog(null,"�޷��ͷ�����ȡ����ϵ");
			String tmp=JOptionPane.showInputDialog("�����������IP�Ͷ˿ں�,��,����", "127.0.0.1,8005");
			if(tmp!=null&&tmp.contains(",")){
				String  tmps[] = tmp.split(",");
				IP  = tmps[0];
				port = tmps[1];	
			}
			return null;
		}
    }
	/**
	 * @param args the command line arguments
	 */
	

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JButton jbtOK;
	private javax.swing.JButton jbtReset;
	private javax.swing.JPasswordField textConfirmPwd;
	private javax.swing.JPasswordField textPwd;
	private javax.swing.JTextField textUserName;
	// End of variables declaration//GEN-END:variables

}