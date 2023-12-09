package concertReservationSystem;

import java.util.*;
import java.util.List;
import java.util.Arrays;
import javax.swing.*;

import user.Login;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

public class ChooseDayAndTime extends JFrame {
	
	private List<String> weekendDates = new ArrayList<>(Arrays.asList("2", "3", "9", "10", "16", "17", "23", "24", "30", "31"));

	private String[] day = {
			"날짜 선택","2023년 12월 1일","2023년 12월 2일","2023년 12월 3일","2023년 12월 4일","2023년 12월 5일","2023년 12월 6일","2023년 12월 7일","2023년 12월 8일",
			"2023년 12월 9일","2023년 12월 10일","2023년 12월 11일","2023년 12월 12일","2023년 12월 13일","2023년 12월 14일","2023년 12월 15일","2023년 12월 16일",
			"2023년 12월 17일","2023년 12월 18일","2023년 12월 19일","2023년 12월 20일","2023년 12월 21일","2023년 12월 22일","2023년 12월 23일","2023년 12월 24일",
			"2023년 12월 25일","2023년 12월 25일","2023년 12월 26일","2023년 12월 27일","2023년 12월 28일","2023년 12월 29일","2023년 12월 30일","2023년 12월 31일"
	};
	
	private String[] time1 = {"시간 선택", "13:00 ~ 15:00", "19:00 ~ 21:00"};		//13~15, 19~21
	private String[] time2 = {"시간 선택", "19:00 ~ 21:00"};						//19~21
	private String[] PeopleCount = {"관람 인원", "1명", "2명"};
	
	private JComboBox<String> scheduleDay = new JComboBox<String>(day);
	private JComboBox<String> scheduleTime1 = new JComboBox<String>(time1);
	private JComboBox<String> scheduleTime2 = new JComboBox<String>(time2);
	private JComboBox<String> NumberOfPeople = new JComboBox<String>(PeopleCount);
	
	private Font comboBoxFont = new Font(null, Font.PLAIN, 14);
	
	private JButton btn = new JButton(" 좌석 선택하기 ");
	
	public ChooseDayAndTime(String id) {
		setTitle("관람일자 선택");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c  = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(scheduleDay);
		c.add(scheduleTime1);		// event 발생으로 주말 값이 아닐경우 time2로 변환
		c.add(scheduleTime2).setVisible(false);
		c.add(NumberOfPeople);
		c.add(btn);
		
		MyItemListener itemListener = new MyItemListener();
		scheduleDay.addItemListener(itemListener);
		scheduleTime1.addItemListener(itemListener);
		scheduleTime2.addItemListener(itemListener);
		NumberOfPeople.addItemListener(itemListener);
		
		scheduleDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				String selectedDay = (String)cb.getSelectedItem();
				String numericDay = extractNumericDay(selectedDay);
				
				if (isWeekend(numericDay)) {	//주말인경우
					scheduleTime2.setVisible(false);
					scheduleTime1.setVisible(true);
				} else {						//평일인경우
					scheduleTime1.setVisible(false);
					scheduleTime2.setVisible(true);
				}
				
			}						
		});
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//new SeatUI(SeatUI).Frame[i].setVisible(true);			//->여기에서 좌석프레임 오픈
				
				 // Access the selected values through the MyItemListener instance
				 // 이후 주석처리해도 상관없는 출력파트(확인용)
				
				//new Seat(id, itemListener.getSelectedDate(), itemListener.getSelectedTime(),
				//		itemListener.getNOPtoInt(), ticketNum, ChooseDayAndTime(id)).setVisible(false);
				
                System.out.println(itemListener.getSelectedDate()
                       + " " + itemListener.getSelectedTime()
                       + " " + itemListener.getSelectedNumberOfPeople());
                System.out.println("날짜와 시간은 String으로 전달");
                
                System.out.println("인원수 int형: " + itemListener.getNOPtoInt());			//인원 수를 String이 아닌 int형으로 받음
                
                System.out.println("id: " + id);
                
			}
        });
		
		scheduleDay.setFont(comboBoxFont);
		scheduleTime1.setFont(comboBoxFont);
		scheduleTime2.setFont(comboBoxFont);
		NumberOfPeople.setFont(comboBoxFont);
		
		setSize(550, 80);
		setVisible(true);
		setLocation(578, 688);
		
		
	}
		
		public boolean isWeekend(String a) {
			//System.out.println(weekendDates.contains(a));		//true, false 반환 확인
	        return weekendDates.contains(a);	//주말인 경우 true
	        									//평일인 경우 false
	    }
		
		
		private static String extractNumericDay(String selectedDay) {	//"12월 **일" 에서 **만 추출 
			Pattern pattern = Pattern.compile("\\d+일");
			Matcher matcher = pattern.matcher(selectedDay);
			if(matcher.find())
				return matcher.group(0).replaceAll("[^0-9]","");
			else 
				return "";
		}
		
		private static String extractNumericNOP(String selectedNumberOfPeople) {	//"12월 **일" 에서 **만 추출 
			Pattern pattern = Pattern.compile("\\d+명");
			Matcher matcher = pattern.matcher(selectedNumberOfPeople);
			if(matcher.find())
				return matcher.group(0).replaceAll("[^0-9]","");
			else 
				return "";
		}
		
		
		
		class MyItemListener implements ItemListener {
			private String selectedDate;			//selected된 각 값이 저장되는 변수		//JComboBox에서 select 될때마다 각 변수에 알아서 저장
			private String selectedTime;
			private String selectedNumberOfPeople;
			
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					JComboBox<String> source = (JComboBox<String>)e.getSource();
					
					if (source == scheduleDay) {
						selectedDate = (String)source.getSelectedItem();
					} else if (source == scheduleTime1 || source == scheduleTime2) {
						selectedTime = (String)source.getSelectedItem();
					} else if (source == NumberOfPeople) {
						selectedNumberOfPeople = (String)source.getSelectedItem();
					}
				}
			}
			
			
			//String으로 받는 각 getter
			public String getSelectedDate() {
				return selectedDate;
			}
			
			public String getSelectedTime() {
				return selectedTime;
			}
			
			public String getSelectedNumberOfPeople() {
				return selectedNumberOfPeople;
			}
			//String으로 받은 NOP를 int형으로 변환해서 받는 getter
			public int getNOPtoInt() {
				return Integer.parseInt(extractNumericNOP(selectedNumberOfPeople));
			}
			
		 }
		
		
		
		
		
		
		//RandomTicketNumber reservationSystem = new RandomTicketNumber();
		//reservationSystem.createNewReservation();
		//public int ticketNum = RandomTicketNumber.getReservationNumber();
		
		
		
		
		
		

/*		public void actionPerformed(ActionEvent e) { 
			int performDate = scheduleDay.getSelectedIndex();			//a[i] => i일
			int performTime1 = scheduleTime1.getSelectedIndex();		
			int performTime2 = scheduleTime2.getSelectedIndex(); 	
			int ticket = NumberOfPeople.getSelectedIndex();				//d[i] = i명
			int nop=0;
			Object time = "";
			Object date = "";
		  
			if(ticket==0 || performTime1==0 || performTime2==0 || performDate==0){ 
			  new Message(new JFrame(""), null,null,"선택하지않은 항목이 있습니다",null,false,null); 
			}else {
			  if(ticket!=0&&(performTime1!=0&&performDate!=0)) {
				  nop+=ticket;
				  time = scheduleTime1.getSelectedItem();
				  date = scheduleDay.getSelectedItem();
			  }else if(ticket!=0&&(performTime2!=0&&performDate!=0)) {
				  nop+=ticket;
				  time = scheduleTime2.getSelectedItem();
				  date = scheduleDay.getSelectedIndex();
			  }
			  ChooseDayAndTime.this.setVisible(false);
			  Seat seat = new Seat(id, date, time, nop,ticketNo, ChooseDayAndTime.this);
			  seat.setVisible(true);
			}
		}
		 
*/		

}
