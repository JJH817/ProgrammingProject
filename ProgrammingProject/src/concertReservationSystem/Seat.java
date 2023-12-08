package concertReservationSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;

public class Seat extends JFrame{
	private String seatGrade; //좌석등급
	JPanel panel, seat;
    JLabel stage, seats[];
    JButton cancel, admit;
    boolean[] seatSelect;
    ArrayList<String> ticket;
    int unReserved; //예약되지않은 좌석수
    
    public Seat(int people) {
    	setSize(1500,1200);
    	setTitle("좌석선택");
    	unReserved = people;
    	panel = new JPanel(null);
    	
    	stage = new JLabel("STAGE");
    	stage.setBackground(Color.white);
    	stage.setBounds(200, 20, 1300, 20);
    	stage.setHorizontalAlignment(SwingConstants.CENTER);
    	
    	cancel = new JButton("취소");
    	admit = new JButton("확인");
    	ticket = new ArrayList<String>();
    	seat = new JPanel(new GridLayout(18, 10, 5, 5));
    	seats = new JLabel[180];
    	seatSelect = new boolean[180];
    	seat.setOpaque(true);
    	Font f = new Font("Arial", Font.PLAIN, 20);
    	for(int i=0;i<3;i++) {
    		for(int j=0;j<10;j++) {
    			int k = i*10+j;
    			seats[k].setBackground(new Color(255, 127, 50));
    			seats[k].setFont(f);
    			seats[k] = new JLabel("vip"+Integer.toString(k+1));
				seats[k].setHorizontalAlignment(JLabel.CENTER);
				seats[k].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {						
						if(seats[k].getBackground()==Color.red) { 					// red는 지금 선택했던 좌석
							seats[k].setBackground(new Color(255, 127, 50)); 						// 다시 선택하면 다시 원래색으로 배경을 바꿔줌
							seatSelect[k] = false;											// 선택된 좌석을 false로 바꿔주고 unReserved를 하나증가시킴
							unReserved++;
						}
						else if(unReserved>0 && seats[k].getBackground()!=Color.black){ 	// black은 이전에 다른사람이 예매한 좌석, unReserved가 아직 0이아니면
							seats[k].setBackground(Color.red);						// 선택할 수 있으므로 red로 색깔을 바꿔주고 seatSelect를 true로해줌
							seatSelect[k] = true;						
							unReserved--;													// 한자리 선택했으므로 unReserved는 감소시킴
						}
						seats[k].setOpaque(true);
					}
				});
				seat.add(seats[k]);
    		}
    	}
    	for(int i=3;i<9;i++) {
    		for(int j=0;j<10;j++) {
    			int k = i*10+j;
    			seats[k].setBackground(new Color(0, 181, 226));
    			seats[k].setFont(f);
    			seats[k] = new JLabel(Integer.toString(k+1)); 				// 라벨을 하나씩 좌석 번호로 초기화해줌
				seats[k].setHorizontalAlignment(JLabel.CENTER);
				seats[k].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {						
						if(seats[k].getBackground()==Color.red) { 					// red는 지금 선택했던 좌석
							seats[k].setBackground(new Color(0, 181, 226)); 						// 다시 선택하면 다시 원래색으로 배경을 바꿔줌
							seatSelect[k] = false;											// 선택된 좌석을 false로 바꿔주고 unReserved를 하나증가시킴
							unReserved++;
						}
						else if(unReserved>0 && seats[k].getBackground()!=Color.black){ 	// green는 이전에 다른사람이 예매한 좌석, unReserved가 아직 0이아니면
							seats[k].setBackground(Color.red);						// 선택할 수 있으므로 red로 색깔을 바꿔주고 seatSelect를 true로해줌
							seatSelect[k] = true;						
							unReserved--;													// 한자리 선택했으므로 unReserved는 감소시킴
						}
						seats[k].setOpaque(true);
					}
				});
				seat.add(seats[k]);
    		}
    	}
    	for(int i=9;i<18;i++) {
    		for(int j=0;j<10;j++) {
    			int k = i*10+j;
    			seats[k].setBackground(new Color(255, 205, 0));
    			seats[k].setFont(f);
    			seats[k] = new JLabel(Integer.toString(k+1)); 				// 라벨을 하나씩 좌석 번호로 초기화해줌
				seats[k].setHorizontalAlignment(JLabel.CENTER);
				seats[k].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {						
						if(seats[k].getBackground()==Color.red) { 					// red는 지금 선택했던 좌석
							seats[k].setBackground(new Color(255, 205, 0)); 						// 다시 선택하면 다시 원래색으로 배경을 바꿔줌
							seatSelect[k] = false;											// 선택된 좌석을 false로 바꿔주고 unReserved를 하나증가시킴
							unReserved++;
						}
						else if(unReserved>0 && seats[k].getBackground()!=Color.black){ 	// green는 이전에 다른사람이 예매한 좌석, unReserved가 아직 0이아니면
							seats[k].setBackground(Color.red);						// 선택할 수 있으므로 red로 색깔을 바꿔주고 seatSelect를 true로해줌
							seatSelect[k] = true;						
							unReserved--;													// 한자리 선택했으므로 unReserved는 감소시킴
						}
						seats[k].setOpaque(true);
					}
				});
				seat.add(seats[k]);
    		}
    	}
    	try {
    		FileReader fr = new FileReader("ticket.txt");
    		BufferedReader br = new BufferedReader(fr);
    		String str;
    		while((str = br.readLine()))
    	}catch(Exception e) {
    		
    	}
    }
    

}

