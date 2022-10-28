package SelfMade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// --------------- MyActionListener --------------- //
// EventListener
// 선택했는데 그 블럭이 지뢰면, setBackground(Color.RED), 그리고 게임 종료
//                지뢰가 아니면, setBackground(Color.GREEN), 게임 계속


// --------------- MineSweeper --------------- //
public class MineSweeper extends JFrame {
	final int SIZE=10;  // 10x10 사이즈 
	JButton[] mineOrNot;                   // mineOrNot 배열로 버튼 만들기(회색, No-text)
	int[] mineField = new int[SIZE*SIZE];  // mineOrNot 버튼과 mineField 배열 값(0 또는 1) 연동
	
	
	MineSweeper(String title){
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 지뢰찾기 게임판 생성
		setLayout(new GridLayout(SIZE,SIZE));
		mineOrNot = new JButton[SIZE*SIZE];
		for(int i=0; i<SIZE*SIZE ; i++) {		
			mineOrNot[i] = new JButton("");
			mineOrNot[i].setBackground(Color.LIGHT_GRAY);
			add(mineOrNot[i]);
		}
		
		for(int i=0; i<SIZE*SIZE ; i++) {  // 지뢰생성 -> 0: - , 1: Mine			
			mineField[i]=(int)(Math.random()*2);
		}
		
		setBounds(500,200,500,500);
		setVisible(true);
	}
	
	public static void main(String[] args) { new MineSweeper("지뢰찾기"); }
} // END - public class MineSweeper