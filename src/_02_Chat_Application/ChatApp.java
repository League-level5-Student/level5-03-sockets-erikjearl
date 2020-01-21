package _02_Chat_Application;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import _00_Click_Chat.gui.ButtonClicker;
import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame {
	JPanel panel;
	
	JTextField tField = new JTextField(15);
	JLabel label = new JLabel("messages:");
	JButton b = new JButton("send");
	
	Server server;
	Client client;
	
	public static void main(String[] args) {
		new ChatApp();
	}
	
	public ChatApp() {
		boolean host = JOptionPane.showInputDialog(null, "Host a connection? ('Yes'/'No')").equals("Yes") ? true : false;
		if(host) {
			server = new Server(8080);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			panel = new JPanel();
			add(panel);
			panel.add(label);
			panel.add(tField);
			panel.add(b);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pack();
			server.start();
		} else {
			setTitle("CLIENT");
			String ipAd = JOptionPane.showInputDialog("what is IP adress");
			String pNum = JOptionPane.showInputDialog("what is port number");
			add(panel);
			panel.add(label);
			panel.add(tField);
			panel.add(b);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
		}
	}

}
