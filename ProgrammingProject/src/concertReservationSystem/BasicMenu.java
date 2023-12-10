package concertReservationSystem;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

//예약버튼, 조회버튼(예약정보창 뜨고 취소버튼 + 확인버튼)
public class BasicMenu extends JFrame {
	JButton reservbtn = new JButton(" 예약하기 ");
	JButton checkbtn = new JButton(" 조회하기 ");
	
    int reservthatTimeNum = 0;
    
	public BasicMenu(String id) {
		setTitle("콘서트 예약 메뉴");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		c.add(reservbtn);
		c.add(checkbtn);
		
		setSize(550,80);
		setLocation(578, 688);
		setVisible(true);	
	


		reservbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChooseDayAndTime(id);
				setVisible(false);
			}
		});
		checkbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try (BufferedReader br = new BufferedReader(new FileReader("src/resources/ticketEx.txt"))) {		//여기부터
		            String line;																					//필요없는 부분 날리고 필요한 reservthatTimeNum값 ++ 연산으로 계산
		            while ((line = br.readLine()) != null) {
		                // "/"를 기준으로 문자열을 분리
		                String[] parts = line.split("/");

		                // 분리된 값을 각각의 변수에 저장
		                String reservationId = parts[0];
		                int date = Integer.parseInt(parts[1]);
		                String timeRange = parts[2];
		                String seatGrade = parts[3];
		                int seatNumber = Integer.parseInt(parts[4]);
		                int reservationNumber = Integer.parseInt(parts[5]);

		                
		                // 사용자가 입력한 ID를 포함하는 경우에만 출력
		                if (id.equals(reservationId)) {
		                    // 출력 예시 (원하는 형태로 수정 가능)
		                    System.out.println("Reservation ID: " + reservationId);
		                    System.out.println("Date: " + date);
		                    System.out.println("Time Range: " + timeRange);
		                    System.out.println("Seat Grade: " + seatGrade);
		                    System.out.println("Seat Number: " + seatNumber);
		                    System.out.println("Reservation Number: " + reservationNumber);
		                    System.out.println("------------");
		                    
		                    reservthatTimeNum++;
		                    
		                    new CheckRes(reservationId, date, timeRange, seatGrade, seatNumber, reservationNumber);
		                    
		                    
		                }
		            } System.out.println(reservthatTimeNum);
		        } catch (IOException ex) {																			//여기까지
		            ex.printStackTrace();
		        }
		    }
		});
	}
	

	
}