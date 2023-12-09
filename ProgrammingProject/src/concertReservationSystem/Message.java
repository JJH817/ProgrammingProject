package concertReservationSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Message extends Dialog implements ActionListener{
	boolean id = false;				//id값을 거짓으로 설정
	Button ok,cancel;					//버튼
	JFrame parent;
	Message(JFrame frame, String id,String ticketNum,String msg,String seats, boolean okcancel,JFrame parent)
	{
		super(frame, "Message", true);		//Message의 프레임 설정
		this.parent = parent;
		
		Container panel = frame.getContentPane();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		if(id!=null) panel.add(new Label(id));
		if(ticketNum!=null) panel.add(new Label(ticketNum));
		if(seats!=null) panel.add(new Label(seats));
		panel.add(new Label(msg));
		add(panel);
		addOKCancelPanel(okcancel);
		createFrame();						//프레임 생성
		pack();
		setVisible(true);
	}

	void addOKCancelPanel( boolean okcancel ) 
	{
		Panel p = new Panel();				//패널 생성
		p.setLayout(new FlowLayout());
		createOKButton( p );				//버튼 생성
		if (okcancel == true)
			createCancelButton( p );
		add("South",p);
	}

	void createOKButton(Panel p) 
	{
		p.add(ok = new Button("OK"));		//초기화후 리스너 설정
		ok.addActionListener(this); 
	}

	void createCancelButton(Panel p) 
	{
		p.add(cancel = new Button("Cancel"));
		cancel.addActionListener(this);
	}

	void createFrame() 
	{
		Dimension d = getToolkit().getScreenSize();		//프레임 생성후 설정
		setLocation(d.width/3,d.height/3);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == ok) 					//버튼 클릭에 따라 액션 설정
		{
			id = true;
			if(parent!=null) parent.dispose();
			new ConcertReservationSystem();
			setVisible(false);
		}
		else if(ae.getSource() == cancel) 
		{
			setVisible(false);
		}
	}
}
