package SelfMade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// --------------- MineSweeper --------------- //
public class MineSweeper extends JFrame {
	final int SIZE=9;  // 10x10 사이즈 
	JButton[] mineOrNot;                            // 선택 배열(버튼)
	String[][] mineField = new String[SIZE][SIZE];  // 지뢰 위치 저장 배열
	String[] whatIsMine = {"◎",""};                 // 지뢰 모양 - mineInstall()
	
	public void mineInstall(){ // 지뢰 10개 생성
		int cnt=0; // 지뢰 갯수(누적 계산)
		
		for(int i=0; i<SIZE ; i++) { // 기본 설정(지뢰가 하나도 없는 상태)
			for(int j=0; j<SIZE ; j++)  mineField[i][j]=whatIsMine[1];
		}
		
		while(cnt<10) { // 지뢰 매설(10개)
			int i = (int)(Math.random()*SIZE);
			int j = (int)(Math.random()*SIZE);
			if(mineField[i][j]==whatIsMine[1]) {mineField[i][j]=whatIsMine[0]; cnt++;}
			else cnt+=0;
		}
	} // END - public void mineInstall()
	
	MineSweeper(String title){
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mineInstall();
		
		setLayout(new GridLayout(SIZE,SIZE));
		mineOrNot = new JButton[SIZE*SIZE];
		for(int i=0; i<SIZE*SIZE ; i++) {		
			mineOrNot[i] = new JButton("");
			mineOrNot[i].setBackground(Color.LIGHT_GRAY);
			mineOrNot[i].addActionListener(new MyActionListener());
			add(mineOrNot[i]);
		}
		
		setBounds(500,200,500,500);
		setVisible(true);
	} // END - MineSweeper(String title){}
	
	// --------------- [ Main Method ] --------------- //
	public static void main(String[] args) { new MineSweeper("지뢰찾기"); }
	
	
	// --------------- MyActionListener --------------- // Event Listener
	// Methods : actionPerformed() , steppedOnTheMine()
	class MyActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
			for(int i=0; i<SIZE*SIZE; i++) {
				if(btn==mineOrNot[i]) {
					mineOrNot[i].setFont(new Font("MS Gothic", Font.BOLD, 15));
					mineOrNot[i].setText(mineField[i/SIZE][i%SIZE]);
					if(mineField[i/SIZE][i%SIZE].equals("◎")) {
						mineOrNot[i].setBackground(Color.RED);
						steppedOnTheMine();
					} else  mineOrNot[i].setBackground(Color.GRAY);
				}
			}
		} // END - public void actionPerformed(ActionEvent e)
	
		public void steppedOnTheMine(){ // 지뢰 밟으면 게임 종료(전체 지뢰 위치 보여줌)
			for(int i=0; i<SIZE*SIZE; i++)
			{   mineOrNot[i].setFont(new Font("MS Gothic", Font.BOLD, 15));
				mineOrNot[i].setText(mineField[i/SIZE][i%SIZE]);
				if(mineField[i/SIZE][i%SIZE].equals("◎"))  mineOrNot[i].setBackground(Color.RED);
				else  mineOrNot[i].setBackground(Color.GRAY);	}
		} // END - public void steppedOnTheMine()
	
	} // END - class MyActionListener implements ActionListener{}
		
} // END - public class MineSweeper extends JFrame{}