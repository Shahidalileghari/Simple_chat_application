package com.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Client  {

	private static final long serialVersionUID = 1L;
	JPanel panel,p,show;
	Box vertical=Box.createVerticalBox();
	JTextField msg;
	JTextField txt;
	JButton send;
	JLabel rep,me,vedio,audio,more,active;
	static JFrame f=new JFrame();
	static DataOutputStream output;
		public Client() {
		
		f.setLayout(null);
		f.getContentPane().setBackground(Color.gray);
		panel=new JPanel();
		panel.setBackground(Color.green);
		panel.setBounds(0,0,400,40);
		f.add(panel);
		panel.setLayout(null);
		rep=new JLabel("\uF0E7");
		rep.setBounds(2,6,60,20);
		rep.setBackground(Color.black);
		rep.setFont(new Font("tohomo",Font.BOLD,20));
		panel.add(rep);
		
	
		me=new JLabel("You");
		me.setBounds(38,4,60,20);
		me.setBackground(Color.white);
		me.setForeground(Color.red);
		me.setFont(new Font("tohomo",Font.BOLD,14));
		panel.add(me);
		
		active=new JLabel("active now");
		active.setBounds(38,16,80,20);
		active.setBackground(Color.white);
		active.setForeground(Color.black);
		active.setFont(new Font("tohomo",Font.BOLD,14));
		panel.add(active);
		
		vedio=new JLabel("vedio");
		vedio.setBounds(210,6,60,20);
		vedio.setBackground(Color.black);
		vedio.setFont(new Font("tohomo",Font.BOLD,14));
		panel.add(vedio);
		
		audio=new JLabel("audio");
		audio.setBounds(290,6,60,20);
		audio.setBackground(Color.black);
		audio.setFont(new Font("tohomo",Font.BOLD,14));
		panel.add(audio);
		
		more=new JLabel("more");
		more.setBounds(340,6,60,20);
		more.setBackground(Color.black);
		more.setFont(new Font("tohomo",Font.BOLD,14));
		panel.add(more);
		
	      p=new JPanel();
		p.setBounds(8,55,370,340);
		p.setBackground(Color.white);
		p.setForeground(Color.black);
		f.add(p);
		
		    msg=new JTextField();
			msg.setBounds(8,398,300,40);
			msg.setBackground(Color.black);
			msg.setBackground(Color.white);
			msg.setFont(new Font("tohomo",Font.BOLD,14));
			f.add(msg);
			
		    send=new JButton("Send");
		    send.setBounds(320,398,58,40);
		    send.setBackground(Color.white);
		    send.setBackground(Color.green);
		    send.setFont(new Font("tohomo",Font.BOLD,8));
		   f.add(send);
			
			
		rep.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
			f.setVisible(false);
			}
		});
		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		     String out=msg.getText();
		    
		     
		     JPanel p2=foramt(out);
		   
		     
		     JPanel left=new JPanel(new BorderLayout());
		      left.add(p2,BorderLayout.LINE_END);
		      vertical.add(left);
		      vertical.add(Box.createHorizontalStrut(15));
		      
		      p.add(vertical,BorderLayout.PAGE_START);
		      
		      vertical.add(Box.createVerticalStrut(1500));
		      p.add(left,BorderLayout.PAGE_START);
		      
		      try {
				output.writeUTF(out);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      msg.setText("");
				f.repaint();
				f.invalidate();
				f.validate();
				
		
			}
		});

      f.  setSize(400,500);
      f.  setLocation(340,150);
      f.  setVisible(true);
		}
		
		public static JPanel foramt(String out)
		{
			JPanel panel=new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			
			JLabel label=new JLabel("<html><p style=\"with:15px\">"+out+"</p></html>");
			label.setFont(new Font("tohomo",Font.BOLD,14));
			label.setBackground(Color.green);
			label.setBorder(new EmptyBorder(15,15,15,50));
			label.setOpaque(true);
			panel.add(label);
			
//			Calendar cal=new Calendar.getInstance();
//			SimpleDateFormat sdf=new SimpleDateFormat("HH:MM");
//			JLabel time=new JLabel();
//			time.setText(sdf.format(Cal.getTime()));
//			
//			panel.add(time);
			
			return panel;
		}
	public static void main(String[] args) {
	
      new Client();
      try {
		Socket s=new Socket("127.0.0.1",6001);
		DataInputStream dip=new DataInputStream(s.getInputStream());
		output=new DataOutputStream(s.getOutputStream());
		while(true)
		{
			String msg=dip.readUTF();
			JPanel panel=foramt(msg);
			JPanel left=new JPanel(new BorderLayout());
			left.add(panel,BorderLayout.LINE_START);
			f.validate();
		}
	} catch (Exception e) {
	
	}
	}

}
