package SelfMade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// --------------- MineSweeper --------------- //
public class MineSweeper extends JFrame {
	
	public static void main(String[] args) { new MineSweeper("지뢰찾기"); }
	
	final int SIZE=9;
	JButton[][] mineOrNot;                          // 좌표 클릭
	String[][] mineField = new String[SIZE][SIZE];  // 지뢰 위치 저장
	String[] whatIsMine = {"◎",""};                 // 지뢰 모양 - mineInstall()
	JPanel recordPane = new JPanel();               // 게임 진행 시간, 상태, 찾은 지뢰 수 
	JPanel gamePane = new JPanel();                 // 지뢰찾기 게임보드
	JLabel time = new JLabel();                     // 시간 표시
	JPanel timePanel = new JPanel();                // 시간 표시(JLabel) 배경
	JTextArea findEmAll = new JTextArea();          // 찾은 지뢰 수 표시(초기값 10)
	boolean wtf;                                    // 지뢰 밟으면 true로 변환
	public Thread timer;
	
	
	public void mineInstall(){ // 지뢰 10개 생성
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
	
	
	// Thread-Method
	public void timeClock(){
		timer = new Thread(){			
			public void run(){
				for(int sec=0;  ; sec++) {
					try {
						time.setText(String.valueOf(sec));
						wait(1000);
						sec++;
					}
					catch(Exception e) { System.out.println(e.getMessage()); }
					if(wtf) break;
				}
			}
		};
		timer.start();
	} // END - public void timeClock
	
	// --------------- MineSweeper --------------- // constructor
	MineSweeper(){}
	MineSweeper(String title){
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		recordPane.setLayout(new BorderLayout());
		recordPane.setBackground(Color.GRAY);
		recordPane.setOpaque(true);
		
		timePanel.setBackground(Color.BLACK);
		time.setFont(new Font("MS Gothic", Font.BOLD, 30));
		time.setForeground(Color.RED);
//		time.setText("TIME");
		timeClock();  //쓰레드
		timePanel.add(time);
		recordPane.add(timePanel, BorderLayout.WEST);
		
		findEmAll.setFont(new Font("MS Gothic", Font.BOLD, 30));
		findEmAll.setBackground(Color.BLACK);
		findEmAll.setForeground(Color.RED);
		findEmAll.append("NUMBER");
		recordPane.add(findEmAll, BorderLayout.EAST);
		
		mineInstall();
		
		gamePane.setLayout(new GridLayout(SIZE,SIZE));
		mineOrNot = new JButton[SIZE][SIZE];
		for(int i=0; i<SIZE ; i++) {
			for(int j=0; j<SIZE ; j++) {
				mineOrNot[i][j] = new JButton(" ");
				mineOrNot[i][j].setFont(new Font("MS Gothic", Font.BOLD, 40));
				mineOrNot[i][j].setBackground(Color.LIGHT_GRAY);
				mineOrNot[i][j].addActionListener(new MyActionListener());
				gamePane.add(mineOrNot[i][j]);
			}
		}
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(recordPane, BorderLayout.NORTH);
		getContentPane().add(gamePane, BorderLayout.SOUTH);
		
		setBounds(500,200,500,550);
		setVisible(true);
		setResizable(false);
	} // END - MineSweeper(String title){}
	
	// --------------- MyActionListener --------------- // Event Listener(actionPerformed()) 
	// Methods : actionPerformed() , stepOnTheMine(), stepOnTheLand(), colorOfNumber(), afterExpansion()
	class MyActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
				for(int i=0; i<SIZE; i++) {
					for(int j=0; j<SIZE; j++) {					
						if((JButton)e.getSource()==mineOrNot[i][j]) {
							mineOrNot[i][j].setFont(new Font("MS Gothic", Font.BOLD, 15));
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
		} // END - public void actionPerformed(ActionEvent e)
	
		public void stepOnTheMine(){ // 게임 종료(지뢰 위치 공개)
			for(int i=0; i<SIZE; i++){
				for(int j=0; j<SIZE; j++) {					
					mineOrNot[i][j].setText(mineField[i][j]);
					if(mineField[i][j].equals("◎")) {
						mineOrNot[i][j].setFont(new Font("MS Gothic", Font.BOLD, 15));
						mineOrNot[i][j].setBackground(Color.RED);
					}
					else {
						mineOrNot[i][j].setText(" ");
						mineOrNot[i][j].setFont(new Font("MS Gothic", Font.BOLD, 40));
						mineOrNot[i][j].setBackground(Color.GRAY);
					}
				}
			}
		} // END - public void steppedOnTheMine()
		
		public void afterExpansion() {
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
		} // END - public void afterExpansion()
		
		private void stepOnTheLand(int row, int col) {
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
					}
				}
			}
		} // END - public void expansion()	
		
		private void colorOfNumber(int row, int col, int mineCnt) {
			
			mineOrNot[row][col].setText(String.valueOf(mineCnt));
			mineOrNot[row][col].setFont(new Font("MS Gothic", Font.BOLD, 20));
			
			if(mineCnt==1)      mineOrNot[row][col].setForeground(Color.BLUE);
			else if(mineCnt==2) mineOrNot[row][col].setForeground(new Color(0, 100, 0)); // GREEN
			else if(mineCnt==3) mineOrNot[row][col].setForeground(Color.RED);
			else if(mineCnt==4) mineOrNot[row][col].setForeground(new Color(0, 0, 75));  // NAVY
			else                mineOrNot[row][col].setForeground(Color.YELLOW);
		} // END - public void mineCount(int row, int col, int mineCnt)
		
		
	} // END - class MyActionListener implements ActionListener{}
		
} // END - public class MineSweeper extends JFrame{}