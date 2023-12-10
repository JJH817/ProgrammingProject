package concertReservationSystem;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CheckRes extends JFrame {
	
	JButton OKbtn = new JButton(" 확인 ");
	JButton Cancelbtn = new JButton("예약취소");
	
	public CheckRes(String id, int date, String time, String seatGrade, int seatNum, int resNum) {
		
		
		setTitle(" 예약 정보 ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null);
		
		JLabel infoID = new JLabel("ID : " + id);
		JLabel infoDate = new JLabel("날짜 : 2023년 12월 " + date + "일");
		JLabel infoTime = new JLabel("시간 : " + time);
		JLabel infoSeatGrade = new JLabel("좌석등급 : " + seatGrade);
		JLabel infoSeatNum = new JLabel("좌석번호 : " + seatNum);
		JLabel infoResNum = new JLabel("예약번호 : " + resNum);
		
		c.add(infoID);
		c.add(infoDate);
		c.add(infoTime);
		c.add(infoSeatGrade);
		c.add(infoSeatNum);
		c.add(infoResNum);
		
		infoID.setBounds(30, 30, 400, 30);
		infoDate.setBounds(30, 80, 400, 30);
		infoTime.setBounds(30, 130, 400, 30);
		infoSeatGrade.setBounds(30, 180, 400, 30);
		infoSeatNum.setBounds(30, 230, 400, 30);
		infoResNum.setBounds(30, 280, 400, 30);
		
		infoID.setFont(new Font(null, Font.PLAIN, 25));
		infoDate.setFont(new Font(null, Font.PLAIN, 25));
		infoTime.setFont(new Font(null, Font.PLAIN, 25));
		infoSeatGrade.setFont(new Font(null, Font.PLAIN, 25));
		infoSeatNum.setFont(new Font(null, Font.PLAIN, 25));
		infoResNum.setFont(new Font(null, Font.PLAIN, 25));
		
		
		//로고2 추가 후 공연 입장 시 주의사항 적기
		
		
		
		c.add(OKbtn);
		c.add(Cancelbtn);
		OKbtn.setBounds(110, 500, 100, 35);
		Cancelbtn.setBounds(240, 500, 100, 35);
		
		OKbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		
		
		
		
		
		//취소 실행시 추가 구현해야할 부분 필요
		//예약을 정말 취소하시겠습니까? 프레임 한번 더 띄우고 해당 창에서 예약취소 버튼을 한번 더 누를시
		//예약취소.
		
		
		Cancelbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		
		setSize(450, 600);
		setVisible(true);		
	}
}

class RealCancel extends JFrame {
	JButton OKbtn = new JButton("");
	JButton realCancelbtn = new JButton("예약취소");
}
