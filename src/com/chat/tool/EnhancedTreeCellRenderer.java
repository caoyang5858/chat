package com.chat.tool;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.chat.bean.NodeInformation;

public class EnhancedTreeCellRenderer extends DefaultTreeCellRenderer{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Icon logo1,logo2,logo3,logo4,logo5,logo6,logo7,logo8,logo9,logo10,logo11; 
	private Icon closed,open;
	public EnhancedTreeCellRenderer() { 
		setBackground(new Color(0,0,0,0));
		logo1 = new ImageIcon("src\\com\\chat\\logo\\1.png"); 
		logo2 = new ImageIcon("src\\com\\chat\\logo\\2.png");
		logo3 = new ImageIcon("src\\com\\chat\\logo\\3.png");
		logo4 = new ImageIcon("src\\com\\chat\\logo\\4.png");
		logo5 = new ImageIcon("src\\com\\chat\\logo\\5.png");
		logo6 = new ImageIcon("src\\com\\chat\\logo\\6.png");
		logo7 = new ImageIcon("src\\com\\chat\\logo\\7.png");
		logo8 = new ImageIcon("src\\com\\chat\\logo\\8.png");
		logo9 = new ImageIcon("src\\com\\chat\\logo\\9.png");
		logo10 = new ImageIcon("src\\com\\chat\\logo\\10.png");
		logo11= new ImageIcon("src\\com\\chat\\logo\\11.png");
		closed = new ImageIcon("src\\com\\chat\\logo\\close.png");
		open = new ImageIcon("src\\com\\chat\\logo\\open.png");
	} 
	public Icon getOpenIcon(){
		return open;
	}
	public Icon getClosedIcon(){
		return closed;
	}
	public Component getTreeCellRendererComponent(JTree tree,Object value,boolean sel,boolean expanded,boolean leaf,int row,boolean hasFocus) { 
		super.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,row,hasFocus); 
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)value; 
		NodeInformation nodeInfo = (NodeInformation)(node.getUserObject()); 
		int type = nodeInfo.getLogoType(); 
		switch(type) { 
		case 1 :setIcon(logo1); 
		break; 
		case 2 :setIcon(logo2); 
		break; 
		case 3 :setIcon(logo3); 
		break; 
		case 4:setIcon(logo4); 
		break; 
		case 5:setIcon(logo5); 
	    break;
		case 6:setIcon(logo6); 
		break; 
		case 7:setIcon(logo7); 
		break; 
		case 8:setIcon(logo8); 
		break; 
		case 9:setIcon(logo9); 
		break; 
		case 10:setIcon(logo10); 
		break; 
		case 11:setIcon(logo11); 
		break; 
		default : 
			break; 
		} 

		return this; 
	} 
} 
