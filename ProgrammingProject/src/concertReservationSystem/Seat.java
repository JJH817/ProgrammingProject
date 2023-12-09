package concertReservationSystem;

import concertReservationSystem.ChooseDayAndTime;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

import java.util.*;
import java.io.*;



public class Seat extends JFrame{
	private static String seatGrade;
	private static HashMap<String, Seat[]> seatmap = new HashMap<>();
	private static HashMap<String, Map<Integer, ChooseDayAndTime>> reservationMap = new HashMap<>();
	JPanel panel, seat; //패널
	JLabel stage, seats[]; 
	JButton cancel, admit; //취소, 확인
	boolean[] seatSelect;
	ArrayList<String> ticket;
	static int unReserved, seatNumber; //  , 좌석번호
	
	public Seat(String id, String date, String time, int people, int ticketNo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1500,1200);
		setTitle("좌석선택");
		unReserved = people;
		panel = new JPanel(null);
		
		stage = new JLabel("Stage"); //무대 패널
		stage.setBackground(Color.white);
		stage.setBounds(200,20,1300,20);
		stage.setHorizontalAlignment(SwingConstants.CENTER);
		
		cancel = new JButton("취소");
		admit = new JButton("확인");
		ticket = new ArrayList<String>();
		seat = new JPanel(new GridLayout(18,10,5,5)); //좌석패널 18행10열, 좌우5픽셀 상하5픽셀 간격을 두고 생성
		seats = new JLabel[180]; //총 180석
		seatSelect = new boolean[180]; //해당 좌석이 예약된 좌석인가를 판별하는 배열
		seat.setOpaque(true);
		int totalReservedSeats = getTotalreservedSeats(id, date, time);

        int maxSeatsAllowed = 2;

        if (totalReservedSeats + 1 >= maxSeatsAllowed) {
        	new Message(new JFrame(""),null,null,"이미 해당 공연의 2자리 이상 예매하셨습니다.",null,false,null);
        }
		Font f = new Font("Arial", Font.PLAIN, 20);
		for(int i=0;i<3;i++) {
			for(int j=0;j<10;j++) {
				int k = i*10+j;
				seats[k].setBackground(new Color(255,127,50)); // 좌석등급 구분을 위해 색을 다르게 설정
				seats[k].setFont(f);
				seats[k] = new JLabel("vip"+Integer.toString(k+1)); //vip석은 앞에서부터 3열
				seats[k].setHorizontalAlignment(JLabel.CENTER);
				seats[k].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						if(seats[k].getBackground()==Color.red) { //red는 예약하려고 클릭한 좌석
							seats[k].setBackground(new Color(255,127,50));//재선택시 vip 배경색으로 변경
							seatSelect[k] = false; //해당자리를 예약하지 않을 것이므로 값을 false로
							unReserved++; //예약하려던 좌석수가 하나증가
						}else if(unReserved>0&&seats[k].getBackground()!=Color.black) {//black은 누군가 이미 예약한 좌석
							seats[k].setBackground(Color.red);//black이 아니면 아직 예약할 수 있는 좌석이고, unReserved가 0이 아니면 
							seatSelect[k] = true;//아직 예약하려는 좌석의 개수가 남아있으므로 red로 변경하고 해당좌석의 seatSelect를 true로
							unReserved--;//에약하려던 자리수를 하나 감소 시킴
						}
						seats[k].setOpaque(true);
					}
				});
				seat.add(seats[k]);//vip석 그리드를 생성
			}
		}
		for(int i=3;i<9;i++) {
			for(int j=0;j<10;j++) {
				int k = i*10+j;
				seats[k].setBackground(new Color(0,181,226)); // 좌석등급 구분을 위해 색을 다르게 설정
				seats[k].setFont(f);
				seats[k] = new JLabel("s"+Integer.toString(k+1)); //vip석 뒷열부터 6줄이 s석
				seats[k].setHorizontalAlignment(JLabel.CENTER);
				seats[k].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						if(seats[k].getBackground()==Color.red) { //red는 예약하려고 클릭한 좌석
							seats[k].setBackground(new Color(0,181,226));//재선택시 s석 배경색으로 변경
							seatSelect[k] = false; //해당자리를 예약하지 않을 것이므로 값을 false로
							unReserved++; //예약하려던 좌석수가 하나증가
						}else if(unReserved>0&&seats[k].getBackground()!=Color.black) {//black은 누군가 이미 예약한 좌석
							seats[k].setBackground(Color.red);//black이 아니면 아직 예약할 수 있는 좌석이고, unReserved가 0이 아니면 
							seatSelect[k] = true;//아직 예약하려는 좌석의 개수가 남아있으므로 red로 변경하고 해당좌석의 seatSelect를 true로
							unReserved--;//에약하려던 자리수를 하나 감소 시킴
						}
						seats[k].setOpaque(true);
					}
				});
				seat.add(seats[k]);//s석 그리드를 생성
			}
		}
		for(int i=9;i<18;i++) {
			for(int j=0;j<10;j++) {
				int k = i*10+j;
				seats[k].setBackground(new Color(255,205,0)); // 좌석등급 구분을 위해 색을 다르게 설정
				seats[k].setFont(f);
				seats[k] = new JLabel("s"+Integer.toString(k+1)); //r석 뒷열부터 9줄이 r석
				seats[k].setHorizontalAlignment(JLabel.CENTER);
				seats[k].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						if(seats[k].getBackground()==Color.red) { //red는 예약하려고 클릭한 좌석
							seats[k].setBackground(new Color(255,205,0));//재선택시 r석 배경색으로 변경
							seatSelect[k] = false; //해당자리를 예약하지 않을 것이므로 값을 false로
							unReserved++; //예약하려던 좌석수가 하나증가
						}else if(unReserved>0&&seats[k].getBackground()!=Color.black) {//black은 누군가 이미 예약한 좌석
							seats[k].setBackground(Color.red);//black이 아니면 아직 예약할 수 있는 좌석이고, unReserved가 0이 아니면 
							seatSelect[k] = true;//아직 예약하려는 좌석의 개수가 남아있으므로 red로 변경하고 해당좌석의 seatSelect를 true로
							unReserved--;//에약하려던 자리수를 하나 감소 시킴
						}
						seats[k].setOpaque(true);
					}
				});
				seat.add(seats[k]);//r석 그리드를 생성
			}
		}
		
		try (BufferedReader br = new BufferedReader(new FileReader("src/resources/ticketEx.txt"))){
			String line;
			while((line = br.readLine())!=null) {
				String[] parts = line.split("/");// "/"를 기준으로 문자열 분리
				
				//분리된 값을 각각의 변수에 저장
				String reservatoinId = parts[0];
				int dateg = Integer.parseInt(parts[1]);
				String timeRange = parts[2];
				String seatGrade = parts[3];
				int seatNumber = Integer.parseInt(parts[4]);
				int reservationNumber = Integer.parseInt(parts[5]);
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		cancel.addActionListener(new ActionListener() {//취소버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChooseDayAndTime(id).setVisible(true);//누르면 다시 날짜 선택창을 띄워줌
				Seat.this.dispose();//좌석선택창을 파기함
			}
		});
		admit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(unReserved>0) {//예약할 좌석수가 남아있으면
					new Message(new JFrame(""),null,null,"좌석을 전부 선택해주세요.",null,false,null);//전부 선택하라는 메시지를 띄워줌
				}else{
					String msg = id+"/"+date+"/"+time+"/";//전부 선택했으면 티켓정보에 들어갈 내용을 만듬
					String seatGrade="";
					String seatsre="";
					String tno = ticketNo+"/";
					//seatSelect[]의 인덱스에 해당하는 좌석의 정보를 생성
					for(int i=0;i<30;i++) {
						if(seatSelect[i]) {
							seatGrade="vip";
							seatsre+=(i+1)+"/";
						}
					}
					for(int i=30;i<90;i++) {
						if(seatSelect[i]) {
							seatGrade="s";
							seatsre+=(i-30+1)+"/";//등급별로 번호를 1번부터 매길 것이기 때문에 인덱스 번호에서 30을 빼주고 자연수에 맞추기위해 1을 더해준다
						}
					}
					for(int i=90;i<180;i++) {
						if(seatSelect[i]) {
							seatGrade="r";
							seatsre+=(i-90+1)+"/";//등급별로 번호를 1번부터 매길 것이기 때문에 인덱스 번호에서 90을 빼주고 자연수에 맞추기위해 1을 더해준다
						}
					}
					seatsre = seatsre.substring(0, seatsre.length()-1);//seatsre의 마지막 문자가"/"이므로 그것을 없애주고 저쟁
					new Message(new JFrame(""),"ID: "+id,"예약번호: "+ticketNo,date+" "+time+"시 공연",seatsre,false,Seat.this);
					msg+=seatGrade+"/"+seatsre+"/"+tno;
					ticket.add(msg);
					try {
						FileWriter fw = new FileWriter("src/resources/ticket.txt");
						BufferedWriter bw = new BufferedWriter(fw);//현 예매정보만 저장할 수 없으므로
						for(int i=0; i<ticket.size();i++) {//반복문으로 모든 예매정보를 다시 입력
							bw.write(ticket.get(i)+"\n");
						}
						bw.close();
						fw.close();
					}catch(Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		cancel.setBounds(1300,1030,100,50);
		admit.setBounds(1300,100,100,50);
		seat.setBounds(200,20,1300,20);
		
		panel.add(stage);
		panel.add(seat);
		panel.add(cancel);
		panel.add(admit);
	}
	private static int getTotalreservedSeats(String id, String date, String time) {
		int totalReservedSeats = 0;
		try(BufferedReader br = new BufferedReader(new FileReader("src/resources/ticket.txt"))){
			String line;
			while((line = br.readLine())!=null){
				String[] parts = line.split("/");//"/"기준으로 분리
				//분리된 값을 각각의 변수에 저장
				String reservationId = parts[0];
				int dateg = Integer.parseInt(parts[1]);
				String timeRange = parts[1];
				
				//같은 날짜, 같은 시간대의 공연을 선택할경우
				if(id.equals(reservationId)&&date.equals(dateg)&&time.equals(timeRange)) {
					totalReservedSeats++;//총 예약좌석수를 증가시킴
				}
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return totalReservedSeats;
	}
}

