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
    
    public Seat(int people, final JFrame parent) {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    		while((str = br.readLine())!= null) {
    			ticket.add(str);
    			StringTokenizer st = new StringTokenizer(str);
    			String name = st.nextToken("\t");
    			String str2 = st.nextToken("\t");
    			str2 = st.nextToken("\t");
    			String seats = st.nextToken("\t");
    			String tim = st.nextToken("\t");
    			StringTokenizer st2 = new StringTokenizer(seats,",");
    			System.out.println(seats);
    		}
    		br.close();
    		fr.close();
    	}catch(Exception e) {
    		
    	}
    	cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true); //날짜선택창을 띄워줌
				Seat.this.dispose(); //좌석선택창을 처분
			}        	
        });
    	admit.addActionListener(new ActionListener(){
    		@Override
			public void actionPerformed(ActionEvent e) {
				if(unReserved>0){
    				new Message(new JFrame("") ,null,null,"좌석을 전부 선택해주세요",null, false,null); 		// member가 0보다 크단것은 좌석을 전부선택 하지 않았다는 뜻
    			}
				else {
					String msg = movieName+"\t"+ticketNum+"\t"+price;								// 그게아니면 예매정보를 아래와 같이 가공함
					String seats = "\t";
					String etc = "시    간 : "+time+"   가     격 : "+price;
					for(int i=0;i<49;i++) {															// 해당하는 좌석이 선택되면 true이기때문에
						if(seatSelect[i])																// true인지 검사하고 맞으면 seats에 추가해줌
							seats+=(i+1)+",";
					}
					seats = seats.substring(0, seats.length()-1);									// seats의 마지막 문자가 ","이기때문에 그것을 없애주기위함
					new Message(new JFrame("") ,"영화 이름 : "+movieName,"예약 번호 : "+ticketNum, etc,"좌     석   : "+seats, false,SeatFrame.this); // 가공한 정보를 msgbox로 띄움
					msg +=seats+"\t"+time;
					ticket.add(msg);																// 리스트에 역시 추가함
					try {
						FileWriter fw = new FileWriter("ticket.txt"); 								// 이제 예매를 했으니 ticket.txt에 쓸것임
						BufferedWriter bw = new BufferedWriter(fw);									// 지금 예매한것만 추가하고싶으나 그럴수없으므로 ticket에 있는 모든 정보를 다시 출력해줌									
						for(int i = 0 ; i<ticket.size();i++)										// 반복문을돌려서 모든 예매정보를 다시 출력해줌 									
							bw.write(ticket.get(i)+"\n");
						bw.close();
						fw.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
    	});
    	cancel.setBounds(1300, 1030, 100, 50);
    	admit.setBounds(1300, 100, 100, 50);
    	seat.setBounds(200, 20, 1300, 20);
    }
    
    

}

