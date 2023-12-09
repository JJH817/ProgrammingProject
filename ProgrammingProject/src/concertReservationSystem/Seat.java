package concertReservationSystem;

import concertReservationSystem.ChooseDayAndTime;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

import java.util.*;
import java.io.*;

public class Seat extends JFrame{
	private static String seatGrade; //좌석등급
	private static HashMap<String, Seat[]> seatMap = new HashMap<>();
	private static HashMap<String, Map<Integer, ChooseDayAndTime>> reservationMap = new HashMap<>();
	JPanel panel, seat;
    JLabel stage, seats[];
    JButton cancel, admit;//취소, 확인
    boolean[] seatSelect;
    ArrayList<String> ticket;
    int unReserved,seatNumber; //예약되지않은 좌석수,좌석번호
    
    public Seat(String id, String date, String time, int people,int ticketNo) {
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
    	hallSeats();
    	
    	while(true) {
    		int totalReservedSeats = new getTotalReservedSeats(String id, String date, String time);
    		int maxSeatsAllowed = 2;
    		if (totalReservedSeats >= maxSeatsAllowed) {
                new Message(new JFrame("예약은 최대 2자리까지 가능합니다"), null,null,null,null, false,null);
            }
    		if (getNOPtoInt == 2) { //2개 예매할 때
                makeReservation(id, date, time, true);
            } else if(getNOPtoInt == 1) { //1개 예매할 때
                makeReservation(id, date, time, false);
            }
    	}
    	
    	try {
    		FileReader fr = new FileReader("src/resources/ticket.txt");
    		BufferedReader br = new BufferedReader(fr);
    		String str;
    		while((str = br.readLine())!= null) {
    			ticket.add(str);
    			StringTokenizer st = new StringTokenizer(str);
    			String str2 = st.nextToken("\t");
    			str2 = st.nextToken("\t");
    			str2 = st.nextToken("\t");
    			String sgrade = st.nextToken();
    			String seat = st.nextToken("\t");
    			String dat = st.nextToken("\t");
    			String tim = st.nextToken("\t");
    			StringTokenizer st2 = new StringTokenizer(sgrade,"\t");
    			StringTokenizer st3 = new StringTokenizer(seat,",");
    			StringTokenizer st4 = new StringTokenizer(dat,"\t");
    			StringTokenizer st5 = new StringTokenizer(tim,"\t");
    			if(seatGrade.equals(sgrade)&&seats.equals(seat)&&date.equals(dat)&&time.equals(tim)) {
    				while(st2.hasMoreTokens()) {
    					str2 = st2.nextToken();
    					int k = Integer.parseInt(str2);
    					this.seats[k].setBackground(Color.black);
    					this.seats[k].setOpaque(true);
    				}
    			}
    		}
    		br.close();
    		fr.close();
    	}catch(Exception e) {
    		
    	}
    	cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ChooseDayAndTime(id).setVisible(true); //날짜선택창을 띄워줌
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
					String msg = id+"\t"+ticketNo+"\t";								// 그게아니면 예매정보를 아래와 같이 가공함
					String seatGrade="\t";
					String seats = "\t";
					String etc = date+"\t"+time+"\t공연";
					for(int i=0;i<30;i++) {															// 해당하는 좌석이 선택되면 true이기때문에
						if(seatSelect[i])																// true인지 검사하고 맞으면 seats에 추가해줌
							seatGrade="VIP";
							seats+=(i+1)+",";
					}
					for(int i=30;i<90;i++) {															// 해당하는 좌석이 선택되면 true이기때문에
						if(seatSelect[i])																// true인지 검사하고 맞으면 seats에 추가해줌
							seatGrade="S";
							seats+=(i+1)+",";
					}
					for(int i=80;i<180;i++) {															// 해당하는 좌석이 선택되면 true이기때문에
						if(seatSelect[i])																// true인지 검사하고 맞으면 seats에 추가해줌
							seatGrade="R";
							seats+=(i+1)+",";
					}
					seats = seats.substring(0, seats.length()-1);									// seats의 마지막 문자가 ","이기때문에 그것을 없애주기위함
					new Message(new JFrame("") ,"ID : "+id,"예약 번호 : "+ticketNo, etc,"좌석: "+seatGrade+"석 "+seats+"번", false,Seat.this); // 가공한 정보를 msgbox로 띄움
					msg +=seatGrade+"\t"+seats+"\t"+etc;
					ticket.add(msg);																// 리스트에 역시 추가함
					try {
						FileWriter fw = new FileWriter("src/resources/ticket.txt"); 								// 이제 예매를 했으니 ticket.txt에 쓸것임
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
    	
    	panel.add(stage);
    	panel.add(seat);
    	panel.add(cancel);
    	panel.add(admit);
    	add(panel);
    }
    public Seat(String seatGrade, int seatNumber) {
		this.seatGrade = seatGrade;
		this.seatNumber = seatNumber;
	}
	private void hallSeats() {
    	seatMap.put("VIP", new Seat[30]);
        seatMap.put("S", new Seat[60]);
        seatMap.put("R", new Seat[90]);
        Font f = new Font(null, Font.PLAIN, 20);
        for(String seatGrade: seatMap.keySet()) {
        	for(int i=0;i<3;i++) {
        		for(int j=0;j<10;j++) {
        			int k = i*10+j;
        			seatMap.get(seatGrade)[k] = new Seat(seatGrade, k + 1);
        			seats[k].setBackground(new Color(255, 127, 50));
        			seats[k].setFont(f);
        			seats[k] = new JLabel("VIP"+Integer.toString(k+1));
    				seats[k].setHorizontalAlignment(JLabel.CENTER);
    				seats[k].setOpaque(true);
    				seat.add(seats[k]);
    				System.arraycopy(seats, 0, seatMap.values(), 0, k);
        		}
        	}
        	for(int i=3;i<9;i++) {
        		for(int j=0;j<10;j++) {
        			int k = i*10-30+j;
        			seatMap.get(seatGrade)[k] = new Seat(seatGrade, k + 1);
        			seats[k].setBackground(new Color(0, 181, 226));
        			seats[k].setFont(f);
        			seats[k] = new JLabel("S"+Integer.toString(k+1)); 				// 라벨을 하나씩 좌석 번호로 초기화해줌
    				seats[k].setHorizontalAlignment(JLabel.CENTER);
    				seats[k].setOpaque(true);
    				seat.add(seats[k]);
    				System.arraycopy(seats, 0, seatMap.values(), 0, k);
        		}
        	}
        	for(int i=9;i<18;i++) {
        		for(int j=0;j<10;j++) {
        			int k = i*10-90+j;
        			seatMap.get(seatGrade)[k] = new Seat(seatGrade, k + 1);
        			seats[k].setBackground(new Color(255, 205, 0));
        			seats[k].setFont(f);
        			seats[k] = new JLabel("R"+Integer.toString(k+1)); 				// 라벨을 하나씩 좌석 번호로 초기화해줌
    				seats[k].setHorizontalAlignment(JLabel.CENTER);
    				seats[k].setOpaque(true);
    				seat.add(seats[k]);
    				System.arraycopy(seats, 0, seatMap.values(), 0, k);
        		}
        	}
        }
    }
    private static void makeReservation(String id, String date, String time, boolean reserveTwoSeat){
    	while(true) {
    		try {
    			if(reserveTwoSeat) {
    				int totalReservedSeats = getTotalReservedSeats(id, date, time);

		            int maxSeatsAllowed = 2;

		            if (totalReservedSeats + 1 >= maxSeatsAllowed) {
		            	new Message(new JFrame("예약은 최대 2자리까지 가능합니다"), null,null,null,null, false,null)
		            }
		            Seat[] seats = seatMap.get(seatGrade);
	                Font f = new Font(null, Font.PLAIN, 20);
	                if(seatMap.containsKey("VIP")) {
	                	for(int i=0;i<3;i++) {
	                		for(int j=0;j<10;j++) {
	                			int k = i*10+j;
	                			seats[k].setBackground(new Color(255, 127, 50));
	                			seats[k].setFont(f);
	                			seats[k] = new JLabel("VIP"+Integer.toString(k+1));
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
	            				System.arraycopy(seats, 0, seatMap.values(), 0, k);
	                		}
	                	}
	                }else if(seatMap.containsKey("S")) {
	                	for(int i=3;i<9;i++) {
	                		for(int j=0;j<10;j++) {
	                			int k = i*10-30+j;
	                			seats[k].setBackground(new Color(0, 181, 226));
	                			seats[k].setFont(f);
	                			seats[k] = new JLabel("S"+Integer.toString(k+1)); 				// 라벨을 하나씩 좌석 번호로 초기화해줌
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
	            				System.arraycopy(seats, 0, seatMap.values(), 0, k);
	                		}
	                	}
	                }else {
	                	for(int i=9;i<18;i++) {
	                		for(int j=0;j<10;j++) {
	                			int k = i*10-90+j;
	                			seats[k].setBackground(new Color(255, 205, 0));
	                			seats[k].setFont(f);
	                			seats[k] = new JLabel("R"+Integer.toString(k+1)); 				// 라벨을 하나씩 좌석 번호로 초기화해줌
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
	            				System.arraycopy(seats, 0, seatMap.values(), 0, k);
	                		}
	                	}
	                }
		            	
		            }else {
		            	int totalReservedSeats = getTotalReservedSeats(id, date, time);

			            int maxSeatsAllowed = 2;

			            if (totalReservedSeats + 1 >= maxSeatsAllowed) {
			            	new Message(new JFrame("예약은 최대 2자리까지 가능합니다"), null,null,null,null, false,null)
			            }
			            Seat[] seats = seatMap.get(seatGrade);
		                Font f = new Font(null, Font.PLAIN, 20);
		                if(seatMap.containsKey("VIP")) {
		                	for(int i=0;i<3;i++) {
		                		for(int j=0;j<10;j++) {
		                			int k = i*10+j;
		                			seats[k].setBackground(new Color(255, 127, 50));
		                			seats[k].setFont(f);
		                			seats[k] = new JLabel("VIP"+Integer.toString(k+1));
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
		            				System.arraycopy(seats, 0, seatMap.values(), 0, k);
		                		}
		                	}
		                }else if(seatMap.containsKey("S")) {
		                	for(int i=3;i<9;i++) {
		                		for(int j=0;j<10;j++) {
		                			int k = i*10-30+j;
		                			seats[k].setBackground(new Color(0, 181, 226));
		                			seats[k].setFont(f);
		                			seats[k] = new JLabel("S"+Integer.toString(k+1)); 				// 라벨을 하나씩 좌석 번호로 초기화해줌
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
		            				System.arraycopy(seats, 0, seatMap.values(), 0, k);
		                		}
		                	}
		                }else {
		                	for(int i=9;i<18;i++) {
		                		for(int j=0;j<10;j++) {
		                			int k = i*10-90+j;
		                			seats[k].setBackground(new Color(255, 205, 0));
		                			seats[k].setFont(f);
		                			seats[k] = new JLabel("R"+Integer.toString(k+1)); 				// 라벨을 하나씩 좌석 번호로 초기화해줌
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
		            				System.arraycopy(seats, 0, seatMap.values(), 0, k);
		                		}
		                	}
		                }
	    			}
    			break;
    			}catch() {
    				
    		}
    	}
    }

    private static int getTotalReservedSeats(String id, String date, String time) {
		// 예매자가 예매한 총 좌석수를 보여주는 method
        int totalReservedSeats = 0;

        for (Map<Integer, ChooseDayAndTime> seatGradeMap : reservationMap.values()) {
            for (ChooseDayAndTime reservation : seatGradeMap.values()) { //이름과 전화번호 모두 일치하는 경우에만 totalReservedSeats 값이 증가. 동명이인이 예매할 경우를 상정
                if (reservation.getId().equals(id)&& ChooseDayAndTime.getSelectedTime().equals(time)&&ChooseDayAndTime.getSelectedDate().equals(date)) {
                    totalReservedSeats += ChooseDayAndTime.getNOPtoInt();
                }	//seat 호출시 받은 해당 id의 예약좌석수가 people에 int로 저장되어있음
            }
        }

        return totalReservedSeats;
    }

}

