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
	
	
	// --------------- MineSweeper --------------- // constructor
	MineSweeper(String title){
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mineInstall();
		
		setLayout(new GridLayout(SIZE,SIZE));
		mineOrNot = new JButton[SIZE][SIZE];
		for(int i=0; i<SIZE ; i++) {
			for(int j=0; j<SIZE ; j++) {
				mineOrNot[i][j] = new JButton("");
				mineOrNot[i][j].setBackground(Color.LIGHT_GRAY);
				mineOrNot[i][j].addActionListener(new MyActionListener());
				add(mineOrNot[i][j]);
			}
		}
		
		setBounds(500,200,500,500);
		setVisible(true);
	} // END - MineSweeper(String title){}
	
	// --------------- MyActionListener --------------- // Event Listener(actionPerformed())
	// Methods : actionPerformed() , steppedOnTheMine(), expansion(), colorOfNumber()
	class MyActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
			for(int i=0; i<SIZE; i++) {
				for(int j=0; j<SIZE; j++) {					
					if(btn==mineOrNot[i][j]) {
						mineOrNot[i][j].setFont(new Font("MS Gothic", Font.BOLD, 15));
						mineOrNot[i][j].setText(mineField[i][j]);
						if(mineField[i][j].equals("◎")) {
							mineOrNot[i][j].setBackground(Color.RED);
							steppedOnTheMine();
						}
						else {
							mineOrNot[i][j].setBackground(Color.GRAY);
							expansion(i,j);
						}
					}
				}
			}
		} // END - public void actionPerformed(ActionEvent e)
	
		public void steppedOnTheMine(){ // 게임 종료(지뢰 위치 공개)
			for(int i=0; i<SIZE; i++){
				for(int j=0; j<SIZE; j++) {					
					mineOrNot[i][j].setFont(new Font("MS Gothic", Font.BOLD, 15));
					mineOrNot[i][j].setText(mineField[i][j]);
					if(mineField[i][j].equals("◎"))  mineOrNot[i][j].setBackground(Color.RED);
					else  mineOrNot[i][j].setBackground(Color.GRAY);
				}
			}
		} // END - public void steppedOnTheMine()
		
		public void expansion(int row, int col) {
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
		
		
		/*   *** 어떻게 하면 회색으로 칠해진 칸만 골라서 expansion()을 할 수 있을까? ***   */
		
		
		private void colorOfNumber(int row, int col, int mineCnt) {
			
			mineOrNot[row][col].setText(String.valueOf(mineCnt));
			mineOrNot[row][col].setFont(new Font("MS Gothic", Font.BOLD, 20));
			
			if(mineCnt==1)      mineOrNot[row][col].setForeground(Color.BLUE);
			else if(mineCnt==2) mineOrNot[row][col].setForeground(new Color(0, 100, 0));
			else if(mineCnt==3) mineOrNot[row][col].setForeground(Color.RED);
			else if(mineCnt==4) mineOrNot[row][col].setForeground(new Color(0, 0, 75));
			else                mineOrNot[row][col].setForeground(Color.YELLOW);
		
		} // END - public void mineCount(int row, int col, int mineCnt)
		
		
	} // END - class MyActionListener implements ActionListener{}
		
} // END - public class MineSweeper extends JFrame{}