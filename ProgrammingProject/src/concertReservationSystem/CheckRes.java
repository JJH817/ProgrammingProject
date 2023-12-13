package concertReservationSystem;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;

public class CheckRes extends JFrame {
	
	private JButton OKbtn = new JButton(" 확인 ");
	private JButton Cancelbtn = new JButton("예약취소");
	private Logo2Panel logo2Panel = new Logo2Panel();

	//사용할 폰트(글자크기)
	private Font InfoFont = new Font(null, Font.PLAIN, 25);
	private Font NoticeFont = new Font(null, Font.PLAIN, 10);
	
	public CheckRes(String id, int date, String time, String seatGrade, int seatNum, int resNum) {
		setTitle(" 예약 정보 ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null);
		
		//컴포넌트 선언
		JLabel infoID = new JLabel("예약자ID : " + id);
		JLabel infoDate = new JLabel("관람일자 : 2023년 12월 " + date + "일");
		JLabel infoTime = new JLabel("관람시간 : " + time);
		JLabel infoSeatGrade = new JLabel("좌석등급 : " + seatGrade);
		JLabel infoSeatNum = new JLabel("좌석번호 : " + seatNum);
		JLabel infoResNum = new JLabel("예약번호 : " + resNum);
		
		JLabel Notice = new JLabel("N O T I C E");
		JLabel Noticeinfo1 = new JLabel("1.입장은 공연 시작 30분 전부터 가능합니다.");
		JLabel Noticeinfo2 = new JLabel("2.공연 시작 후 입장은 직원에게 안내받아 주십시오.");
		JLabel Noticeinfo3 = new JLabel("3.다른 사람에게 방해가 될만한 소음이나 행동은 삼가주시기 바랍니다.");
		JLabel Noticeinfo4_1 = new JLabel("4.고휘도의 응원봉 사용시 타 관람객분들의 방해가 될 수 있어 퇴장조치하니");
		JLabel Noticeinfo4_2 = new JLabel("  양해부탁드립니다.");
		
		//컴포넌트 add
		c.add(infoID);
		c.add(infoDate);
		c.add(infoTime);
		c.add(infoSeatGrade);
		c.add(infoSeatNum);
		c.add(infoResNum);
		
		c.add(Notice);
		c.add(Noticeinfo1);
		c.add(Noticeinfo2);
		c.add(Noticeinfo3);
		c.add(Noticeinfo4_1);
		c.add(Noticeinfo4_2);
		
		//컴포넌트 위치,크기,폰트 조정
		infoID.setBounds(30, 30, 400, 30);
		infoDate.setBounds(30, 80, 400, 30);
		infoTime.setBounds(30, 130, 400, 30);
		infoSeatGrade.setBounds(30, 180, 400, 30);
		infoSeatNum.setBounds(30, 230, 400, 30);
		infoResNum.setBounds(30, 280, 400, 30);
		
		Notice.setBounds(200, 380, 300, 30);
		Noticeinfo1.setBounds(110, 410, 300, 15);
		Noticeinfo2.setBounds(110, 425, 300, 15);
		Noticeinfo3.setBounds(110, 440, 300, 15);
		Noticeinfo4_1.setBounds(110, 455, 300, 15);
		Noticeinfo4_2.setBounds(110, 470, 300, 15);
		
		infoID.setFont(InfoFont);
		infoDate.setFont(InfoFont);
		infoTime.setFont(InfoFont);
		infoSeatGrade.setFont(InfoFont);
		infoSeatNum.setFont(InfoFont);
		infoResNum.setFont(InfoFont);
		
		Noticeinfo1.setFont(NoticeFont);
		Noticeinfo2.setFont(NoticeFont);
		Noticeinfo3.setFont(NoticeFont);
		Noticeinfo4_1.setFont(NoticeFont);
		Noticeinfo4_2.setFont(NoticeFont);
		
		//이미지패널(로고) 컴포넌트 추가
		logo2Panel.setBounds(10, 410, 90, 70);
		c.add(logo2Panel);
		logo2Panel.setBackground(Color.WHITE);
		
		//확인 및 예약취소 버튼 컴포넌트 추가 및 위치,크기 지정
		c.add(OKbtn);
		c.add(Cancelbtn);
		OKbtn.setBounds(110, 500, 100, 35);
		Cancelbtn.setBounds(240, 500, 100, 35);
		
		//확인 버튼 시 예약정보 창 닫기
		OKbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		//취소 실행시 한번 더 물어보는 프레임 오픈
		Cancelbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RealCancel(resNum);
			}
		});
		
		setSize(450, 600);
		setVisible(true);		
	}
}

//예약정보에 사용될 로고
class Logo2Panel extends JPanel {
	
	private ImageIcon icon2 = new ImageIcon("src/resources/logo2.png");
	private Image img2 = icon2.getImage();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();
		g.drawImage(img2, 0, 0, d.width, d.height, null);
	}
}

//예약취소 버튼 시 ticket.txt 파일에서 해당 정보 삭제하는 프레임
class RealCancel extends JFrame {
	JButton Yesbtn = new JButton("예");
	JButton Nobtn = new JButton("아니오");
	
	public RealCancel(int resNum) {
		setTitle("취소 확인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		//취소확인 문구
		JLabel Question1 = new JLabel("동일한 예약번호의 예약이 모두 취소됩니다.");
		JLabel Question2 = new JLabel("정말로 예약을 취소하시겠습니까?");
		//정말로 취소하시겠습니까? 문구를 프레임에 넣어야함.
		c.add(Yesbtn);
		c.add(Nobtn);
		c.add(Question1);
		c.add(Question2);
		
		Question1.setBounds(30, 15, 250, 15);
		Question2.setBounds(30, 25, 250, 25);
		Yesbtn.setBounds(45, 60, 80, 30);
		Nobtn.setBounds(155, 60, 80, 30);
		
		//예 선택 시 txt파일에서 해당 정보 삭제
		Yesbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//파일 읽어서 해당 부분에서 id, 날짜, 시간, 좌석, 예약번호 모두 검사 후 제거
				//or 예약번호에 해당하는 줄 전체 제거.
				
		        List<String> lines2 = new ArrayList<>();
		        try (BufferedReader br = new BufferedReader(new FileReader("src/resources/ticketEx.txt"))) {
		            String line;
		            while ((line = br.readLine()) != null) {
		                // 마지막 값 추출
		                String[] parts = line.split("/");
		                int lastValue = Integer.parseInt(parts[parts.length - 1]);		//파일에 저장된 예약정보의 마지막요소(예약번호)를 int형으로 변환(저장형식 오류가능성때문)

		                // resNum과 비교하여 일치하지 않으면 리스트에 추가
		                if (lastValue != resNum) {
		                    lines2.add(line);
		                }
		            }
		        } catch (IOException e2) {
		            e2.printStackTrace();
		        }

		        // 파일 덮어쓰기
		        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/resources/ticketEx.txt"))) {
		            for (String line : lines2) {
		                bw.write(line);
		                bw.newLine();
		            }
		        } catch (IOException e2) {
		            e2.printStackTrace();
		        }
		        
		        setVisible(false);
			}
		});
		
		//아니오 선택 시 창 닫기(visible(false))
		Nobtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	
		setSize(300,150);
		setVisible(true);
	}
	
}
