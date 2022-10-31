package SelfMade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// --------------- MineSweeper --------------- //
public class MineSweeper extends JFrame {
	final int SIZE=9;  // 9x9 사이즈 
	JButton[][] mineOrNot;                            // 선택 배열(버튼)
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
	
	// --------------- [ Main Method ] --------------- //
	public static void main(String[] args) { new MineSweeper("지뢰찾기"); }
	
	
	// --------------- MyActionListener --------------- // Event Listener
	// Methods : actionPerformed() , steppedOnTheMine()
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
							numberOfMine(i,j);
						}
					}
				}
			}
		} // END - public void actionPerformed(ActionEvent e)
	
		public void steppedOnTheMine(){ // 지뢰 밟으면 게임 종료(전체 지뢰 위치 보여줌)
			for(int i=0; i<SIZE; i++){
				for(int j=0; j<SIZE; j++) {					
					mineOrNot[i][j].setFont(new Font("MS Gothic", Font.BOLD, 15));
					mineOrNot[i][j].setText(mineField[i][j]);
					if(mineField[i][j].equals("◎"))  mineOrNot[i][j].setBackground(Color.RED);
					else  mineOrNot[i][j].setBackground(Color.GRAY);
				}
			}
		} // END - public void steppedOnTheMine()
		
		public void numberOfMine(int row, int col) { // 클릭한 지점을 기준으로 확장한 후, 주위 칸에 지뢰가 몇개 있는지 표시  
			int[] rows = {row-1, row, row+1};
			int[] cols = {col-1, col, col+1};
			for(int i=0; i<rows.length; i++){
				for(int j=0; j<cols.length; j++){
					if(rows[i]>8 || cols[j]>8) break;
					else if(rows[i]<0 || cols[j]<0) {
						if(rows[i]<0 && cols[j]>0) mineOrNot[(-1)*rows[i]][cols[j]].setBackground(Color.GRAY);
						else if(rows[i]>0 && cols[j]<0) mineOrNot[rows[i]][(-1)*cols[j]].setBackground(Color.GRAY);
						else if(rows[i]<0 && cols[j]<0) mineOrNot[(-1)*rows[i]][(-1)*cols[j]].setBackground(Color.GRAY); 
					}
					else mineOrNot[rows[i]][cols[j]].setBackground(Color.GRAY);
				}
			}
		} // END - public void numberOfMine()
		/* 일단, 누른 지점으로부터 영역 확장되는건 구현함
		   근데, 실제 게임에서는 확장된 영역의 각 칸에서 또 확장 - 걸리는 지뢰가 없을 때까지
		   만약, 주위 칸에 지뢰가 있다면, 그 갯수 표시 - 갯수에 따른 색상 4:NAVY/ 3:RED / 2:GREEN / 1:BLUE
		   비록, 위 메서드의 코드는 사용하지 않을 것 같지만, 예외 발생 원인은 주의하기
		   이전 코드 실행 시, cols[i]=-1일 때, 이중 for문의 내부 for문에서 break작동해서, 색칠되야할 부분까지도 제외됬었음.
		   향후 작업시 이 점 고려하기
		   향후 방향 1) : 어느 한 칸을 눌렀을 때, 1)영역 확장 이랑 2) 주위 칸의 지뢰 갯수 카운팅 으로 나눠서 작업
		   향후 방향 2) : https://minesweeper.online/screen/1709183266-hd-24.png , 디자인 보완
		*/
		
		
	} // END - class MyActionListener implements ActionListener{}
		
} // END - public class MineSweeper extends JFrame{}