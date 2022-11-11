package SelfMade;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

//*** 3. Layout 조정
//*** 5. 코드 정리(가독성 높이기) - 쓰레드에 사용된 메서드는 쓰레드 코드 상단으로, 이벤트 리스너에 사용된 메서드는 이벤트 리스너 코드 하단으로

public class MineSweeper extends JFrame {
	
	// ----------------------- [ Main Method ] ---------------------- //
	public static void main(String[] args) { new MineSweeper("지뢰찾기"); }
	// -------------------------------------------------------------- //
	
	
	final int SIZE=9;                               // 9x9
	JButton[][] mineOrNot;                          // 클릭 버튼 설정용 배열 
	String[][] mineField = new String[SIZE][SIZE];  // 지뢰 위치 설정
	String[] whatIsMine = {"◎",""};                 // 지뢰 모양
	
	JPanel recordPane = new JPanel();               // timePanel(스톱워치), restart(상태 및 재시작), flagPanel(찾은 지뢰 수) add할 Panel 
	JPanel gamePane = new JPanel();                 // 지뢰찾기 게임이 이뤄지는 Panel
	
	JLabel time = new JLabel();                     // 스톱워치
	JPanel timePanel = new JPanel();                // 스톱워치(time-JLabel) 배경
	public Thread timer;                            // 스톱워치 작동 Thread
	boolean gameStart;                              // true일 때부터 시간 흐름 - 첫 클릭 후 true로 전환
	static String timeRc;                           // 게임 종료 시 시간 계산
	
	JButton restart = new JButton();                // 상태 및 재시작
	
	JLabel findEmAll = new JLabel();                // 찾은 지뢰 수(초기값 10, 다 찾으면 0)
	JPanel flagPanel = new JPanel();                // 찾은 지뢰 수(findEmAll-JLabel) 배경
	
	boolean wtf;                                    // 지뢰 밟으면 true로 변환
	
	long startTime;                                 // 게임 시작 시간
	long endTime;                                   // 게임 종료 시간
	
	boolean gameResult;                             // 게임 승리 시 true로 변환
	// ------------------------- 멤버변수 정리 ------------------------- //

	// --------------- 메서드, 생성자, 쓰레드, 이벤트 리스너 ------------------ //	
	public void mineInstall(){  // 지뢰 생성(10개)
		int cnt=0;
		
		for(int i=0; i<SIZE ; i++) {
			for(int j=0; j<SIZE ; j++)  mineField[i][j]=whatIsMine[1];
		}
		
		while(cnt<10) {
			int i = (int)(Math.random()*SIZE);
			int j = (int)(Math.random()*SIZE);
			if(mineField[i][j]==whatIsMine[1]) {mineField[i][j]=whatIsMine[0]; cnt++;}
			else cnt+=0;
		}
	} // END - public void mineInstall()
	
	public void flagCounter() {  // flag(▲, 우클릭된 칸) 갯수 카운트
		int Cnt =10;
		String flagCnt;
		
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				if(mineOrNot[i][j].getText()=="▲") {
					Cnt--;
					if(Cnt<0) Cnt=0;
				}
			}
		}
		
		flagCnt = String.format("%03d", Cnt);
		findEmAll.setText(String.valueOf(flagCnt));
	} // END - public void flagCounter()
	
	public void survived() {  // 게임 종료 탐지		
		int noMinesUnderMyFoot=0;
		
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				if(mineField[i][j]==whatIsMine[1] && mineOrNot[i][j].getBackground()==Color.GRAY) {
					noMinesUnderMyFoot++;
				}
			}
		}
		
		if(noMinesUnderMyFoot == SIZE*SIZE-10) gameResult=true;
	} // END - public void survived()
	
	public void timeClock(){  // Thread-Method(스톱워치)
		timer = new Thread(){			
			public void run(){
				String tRecord;
				
				while(gameStart==false) {
					time.setText("000");
					if(gameStart) break;
				}
				
				// https://stackify.com/heres-how-to-calculate-elapsed-time-in-java/ 
				startTime=System.currentTimeMillis();
				
				for(int sec=0;  ; sec++) {
					try {
						if(sec!=0)sleep(1000);
						tRecord = String.format("%03d", sec);
						time.setText(tRecord);
					}
					catch(Exception e){ System.out.println(e.getMessage()); }
					if(wtf || gameResult) {break;}
				}
				
				endTime=System.currentTimeMillis();
				
				// long(정수형) => float(실수형) => String(문자형)
				float f_timeRc = Float.valueOf(endTime-startTime);      
				timeRc = String.format("Time : %.4fsec", f_timeRc/1000);
				
				new MineSweeper(gameResult);  // 게임 종료(승리) 창 생성
			}
		};
		timer.start();
	} // END - public void timeClock(){}
	
	// ----------------- [ Constructor ] ----------------- // begin 
	MineSweeper(String title){  // 기본 생성자
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		recordPane.setLayout(new FlowLayout(FlowLayout.CENTER, 90, 5));
		recordPane.setBackground(Color.LIGHT_GRAY);
		
		timePanel.setBackground(Color.BLACK);
		time.setFont(new Font("MS Gothic", Font.BOLD, 30));
		time.setForeground(Color.RED);
		timeClock();  //쓰레드
		timePanel.add(time);
		recordPane.add(timePanel);
		
		restart.setBackground(Color.LIGHT_GRAY);
		restart.setText("●");
		restart.setForeground(Color.YELLOW);
		restart.setBorder(null);
		restart.setFont(new Font("MS Gothic", Font.BOLD, 45));
		restart.addActionListener(new MyActionListener());
		recordPane.add(restart);
		
		flagPanel.setBackground(Color.BLACK);
		findEmAll.setFont(new Font("MS Gothic", Font.BOLD, 30));
		findEmAll.setForeground(Color.RED);
		findEmAll.setText("010");
		flagPanel.add(findEmAll);
		recordPane.add(flagPanel);

		mineInstall();
		
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		// https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
		
		gamePane.setLayout(new GridLayout(SIZE,SIZE, 2, 2));
		mineOrNot = new JButton[SIZE][SIZE];
		for(int i=0; i<SIZE ; i++) {
			for(int j=0; j<SIZE ; j++) {
				mineOrNot[i][j] = new JButton(" ");
				mineOrNot[i][j].setFont(new Font("MS Gothic", Font.BOLD, 40));
				mineOrNot[i][j].setBackground(Color.LIGHT_GRAY);
				mineOrNot[i][j].setBorder(raisedbevel);
				mineOrNot[i][j].addActionListener(new MyActionListener());
				mineOrNot[i][j].addMouseListener(new MyActionListener());
				gamePane.add(mineOrNot[i][j]);
			}
		}
		
		getContentPane().setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(recordPane, BorderLayout.NORTH);
		getContentPane().add(gamePane, BorderLayout.CENTER);
		getContentPane().add(new JPanel(), BorderLayout.EAST);
		getContentPane().add(new JPanel(), BorderLayout.WEST);
		getContentPane().add(new JPanel(), BorderLayout.SOUTH);
		
		setBounds(500,200,500,600);
		setVisible(true);
		setResizable(false);
	} // END - MineSweeper(String title){}
	
	MineSweeper(Boolean gameResult){  // 게임 승리 시 출력(실행)할 생성자
		if(gameResult) {
			getContentPane().setBackground(Color.WHITE);
			getContentPane().setLayout(new BorderLayout());
			
			JLabel tR = new JLabel();
			tR.setText(timeRc);
			getContentPane().add(tR, BorderLayout.NORTH);
			
			JLabel Cong = new JLabel();
			Cong.setFont(new Font("MS Gothic", Font.BOLD|Font.ITALIC, 17));
			Cong.setForeground(Color.BLUE);
			Cong.setText("CONGRATULATIONS!!!");
			Cong.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(Cong, BorderLayout.CENTER);
			
			JLabel createdBy = new JLabel();
			createdBy.setText("created by SiHoonChris");
			createdBy.setHorizontalAlignment(SwingConstants.RIGHT); // https://stackoverflow.com/questions/12589494/align-text-in-jlabel-to-the-right
			getContentPane().add(createdBy, BorderLayout.SOUTH);
			
			setBounds((int)(500*1.3), (int)(200*1.6), 200, 300);
			setResizable(false);
			setVisible(gameResult);
		}
		
	} // END - MineSweeper(Boolean gameResult){}
	// ----------------- [ Constructor ] ----------------- // end
	
	// ----------------- [ Event Listener ] ----------------- // begin
	// Methods : mousePressed(), actionPerformed() , stepOnTheMine(), afterExpansion(), stepOnTheLand(), colorOfNumber()
	class MyActionListener extends MouseAdapter implements ActionListener{
		public void mousePressed(MouseEvent e) {
			// https://imhotk.tistory.com/378
			for(int i=0 ; i<SIZE ; i++) {
				for(int j=0 ; j<SIZE ; j++) {					
					if(e.getButton()==MouseEvent.BUTTON3 && e.getSource()==mineOrNot[i][j]) {
						
						gameStart=true;  // 마우스 우클릭도 게임 시작으로 처리
						
						// 이미 눌린 칸(Color.GRAY)에는 ▲ 입력 못하게 작성 
						if(mineOrNot[i][j].getText() != "▲" && mineOrNot[i][j].getBackground() != Color.GRAY){
							mineOrNot[i][j].setFont(new Font("MS Gothic", Font.BOLD, 20));
							mineOrNot[i][j].setText("▲");
							flagCounter();
						}
						
						else {  //  이미 ▲가 있는 칸에 우클릭하면 ▲가 없어짐
							if(mineOrNot[i][j].getText() == "▲") {								
								mineOrNot[i][j].setFont(new Font("MS Gothic", Font.BOLD, 20));
								mineOrNot[i][j].setText(" ");
								flagCounter();
							}
						}
						
					}
				}
			}
		} // END - public void mousePressed(MouseEvent e)
		
		public void actionPerformed(ActionEvent e) {
			if((JButton)e.getSource()==restart) {
				setVisible(false);
				dispose();
				new MineSweeper("지뢰찾기");
			}
			else {
				gameStart=true;
				for(int i=0; i<SIZE; i++) {
					for(int j=0; j<SIZE; j++) {					
						if((JButton)e.getSource()==mineOrNot[i][j]) {
							mineOrNot[i][j].setFont(new Font("MS Gothic", Font.BOLD, 20));
							mineOrNot[i][j].setText(mineField[i][j]);
							
							if(mineField[i][j].equals("◎")) {
								mineOrNot[i][j].setBackground(Color.RED);
								stepOnTheMine();
								wtf=true;
							}
							else {
								mineOrNot[i][j].setBackground(Color.GRAY);
								stepOnTheLand(i,j);
								afterExpansion();
							}
						}
					}
				}		
			}
		} // END - public void actionPerformed(ActionEvent e)
		
		public void stepOnTheMine(){  // 게임 종료(지뢰 위치 공개)
			restart.setForeground(Color.BLACK);
			
			for(int i=0; i<SIZE; i++){
				for(int j=0; j<SIZE; j++) {					
					if(mineOrNot[i][j].getText()==" ") {						
						mineOrNot[i][j].setText(mineField[i][j]);
						if(mineField[i][j].equals("◎")) {
							mineOrNot[i][j].setFont(new Font("MS Gothic", Font.BOLD, 20));
							mineOrNot[i][j].setBackground(new Color(250, 250, 150));
						}
						else {
							mineOrNot[i][j].setText(" ");
							mineOrNot[i][j].setFont(new Font("MS Gothic", Font.BOLD, 40));
						}
					}
					
					else if(mineOrNot[i][j].getText()=="▲") { //2-1. ▲표시 했어도 지뢰 밟으면 노란색 배경 적용-지뢰표시는 그대로
						if(mineField[i][j].equals("◎")) mineOrNot[i][j].setBackground(new Color(250, 250, 150));
					}
					
				}
			}
		} // END - public void steppedOnTheMine()
		
		public void afterExpansion() {  // 버튼 클릭 후 확장
			int prv_cnt=0;
			
			while(true) {
				int prs_cnt=0;
				
				for(int i=0; i<SIZE; i++) {
					for(int j=0; j<SIZE; j++) {
						if(mineOrNot[i][j].getBackground()==Color.GRAY){
							stepOnTheLand(i, j);
							prs_cnt++;
						}
					}
				}
				if(prs_cnt!=prv_cnt) prv_cnt=prs_cnt;
				else break;
			}
			
			flagCounter();
			survived();
		} // END - public void afterExpansion()
		
		private void stepOnTheLand(int row, int col) {  // 지뢰 없는 칸 밟았을 때
			int mineCnt=0;
			int row_min_lmt, row_max_lmt, col_min_lmt, col_max_lmt;
			if(row==0)      row_min_lmt=0;
			else            row_min_lmt=row-1;
			if(col==0)      col_min_lmt=0;
			else            col_min_lmt=col-1;
			if(row==SIZE-1) row_max_lmt=SIZE-1;
			else            row_max_lmt=row+1;
			if(col==SIZE-1) col_max_lmt=SIZE-1;
			else            col_max_lmt=col+1;
			
			for(int i=row_min_lmt; i<=row_max_lmt; i++) {
				for(int j=col_min_lmt; j<=col_max_lmt; j++) {
					if(mineField[i][j].equals("◎")) mineCnt++;
				}
			}
			
			if(mineCnt!=0) colorOfNumber(row, col, mineCnt);
			else {
				for(int i=row_min_lmt; i<=row_max_lmt; i++) {
					for(int j=col_min_lmt; j<=col_max_lmt; j++) {
						if(!mineField[i][j].equals("◎")) mineOrNot[i][j].setBackground(Color.GRAY);
						if(mineOrNot[i][j].getText()=="▲") mineOrNot[i][j].setText(" ");  // else if 말고 if 사용
					}
				}
			}
		} // END - private void stepOnTheLand(int row, int col)	
		
		private void colorOfNumber(int row, int col, int mineCnt) {  // 지뢰 개수 표시 및 색깔 구분
			mineOrNot[row][col].setText(String.valueOf(mineCnt));
			mineOrNot[row][col].setFont(new Font("MS Gothic", Font.BOLD, 25));
			
			if(mineCnt==1)      mineOrNot[row][col].setForeground(Color.BLUE);
			else if(mineCnt==2) mineOrNot[row][col].setForeground(new Color(0, 100, 0)); // GREEN
			else if(mineCnt==3) mineOrNot[row][col].setForeground(Color.RED);
			else if(mineCnt==4) mineOrNot[row][col].setForeground(new Color(0, 0, 75));  // NAVY
			else                mineOrNot[row][col].setForeground(Color.YELLOW);
		} // END - private void colorOfNumber(int row, int col, int mineCnt)
		
	} // END - class MyActionListener implements ActionListener{}
	// ----------------- [ Event Listener ] ----------------- // end
	
} // END - public class MineSweeper extends JFrame{}