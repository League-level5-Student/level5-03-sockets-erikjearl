package _02_Chat_Application;

import java.awt.LayoutManager;
import java.util.ArrayDeque;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame {
	JPanel panel;
	JPanel send;
	JPanel messages;
	
	JTextField tField = new JTextField(15);
	JLabel label;
	JButton b = new JButton("send");
	
	Server server;
	Client client;
	
	ArrayDeque<String> messageList = new ArrayDeque<>();
	          
	
	public static void main(String[] args) {
		new ChatApp();
	}
	
	public ChatApp() {
		boolean host = JOptionPane.showInputDialog(null, "Host a connection? ('Yes'/'No')").equals("Yes") ? true : false;
		if(host) {
			server = new Server(this, 8080);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			panel = new JPanel();
			messages = new JPanel();
			send = new JPanel();
			label = new JLabel("Server messages:");
			
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			
			panel.add(messages);
			messages.add(label);
			messages.setSize(400, 200);
			
			add(panel);
			panel.add(send);
			send.add(tField);
			
			b.addActionListener((e)->{
				server.sendMessage(tField.getText());
				tField.setText("");
			});
			
			send.add(b);
			
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pack();
			
			server.start();
		} else {
			setTitle("CLIENT");
			String ipAd = JOptionPane.showInputDialog("what is IP adress");
			String pNum = JOptionPane.showInputDialog("what is port number");
			int port = Integer.parseInt(pNum);
			client = new Client(this, ipAd, port);
			panel = new JPanel();
			messages = new JPanel();
			send = new JPanel();
			label = new JLabel("Client messages:");
			
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			
			panel.add(messages);
			messages.add(label);
			messages.setSize(400, 200);
			
			add(panel);
			panel.add(send);
			send.add(tField);
			
			b.addActionListener((e)->{
				client.sendMessage(tField.getText());
				tField.setText("");
			});
			
			send.add(b);
			
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pack();
			client.start();
		}
		
	}

	public void addMessage(String txt) {
		messageList.add(txt);
		updateMessages();
	}
	
	public void updateMessages() {
		String txt = "";
		for(String s : messageList) {
			txt += "" + s + "\n";
		}
		if(messageList.size() > 8)
			messageList.removeFirst();
		label.setText(txt);
	}
	
}
