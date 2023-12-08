package concertReservationSystem;

import java.util.*;
import java.util.List;
import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

public class ChooseDayAndTime extends JFrame {
	
	private List<String> weekendDates = new ArrayList<>(Arrays.asList("2", "3", "9", "10", "16", "17", "23", "24", "30", "31"));

	private String[] day = {
			"날짜선택","12월 1일","12월 2일","12월 3일","12월 4일","12월 5일","12월 6일","12월 7일","12월 8일","12월 9일","12월 10일","12월 11일",
			"12월 12일","12월 13일","12월 14일","12월 15일","12월 16일","12월 17일","12월 18일","12월 19일","12월 20일","12월 21일","12월 22일",
			"12월 23일","12월 24일","12월 25일","12월 25일","12월 26일","12월 27일","12월 28일","12월 29일","12월 30일","12월 31일"
	};
	private String[] time1 = {"시간선택", "A~B", "C~D"};
	private String[] time2 = {"시간선택", "C~D"};
	private String[] PeopleCount = {"인원수", "1명", "2명"};
	private JComboBox<String> scheduleDay = new JComboBox<String>(day);
	private JComboBox<String> scheduleTime1 = new JComboBox<String>(time1);
	private JComboBox<String> scheduleTime2 = new JComboBox<String>(time2);
	private JComboBox<String> NumberOfPeople = new JComboBox<String>(PeopleCount);
	JButton btn = new JButton("다음");
	
	//새 프레임 호출.
	
	public ChooseDayAndTime() {
		setTitle("날짜와 시간을 골라주세요.");
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
				//int index = cb.getSelectedIndex();	//이 부분은 현시점에는 필요없을거같음
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
                System.out.println(itemListener.getSelectedDate()
                       + " " + itemListener.getSelectedTime()
                       + " " + itemListener.getSelectedNumberOfPeople());
			}
        });
		
		setSize(720, 480);
		setVisible(true);
				
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
		
		class MyItemListener implements ItemListener {
			private String selectedDate;
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
			
			public String getSelectedDate() {
				return selectedDate;
			}
			public String getSelectedTime() {
				return selectedTime;
			}
			public String getSelectedNumberOfPeople() {
				return selectedNumberOfPeople;
			}
		}
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			new ChooseDayAndTime();
		}
	

}
